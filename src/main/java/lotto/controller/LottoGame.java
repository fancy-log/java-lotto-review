package lotto.controller;

import java.util.ArrayList;
import java.util.List;

import lotto.model.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoRandomNumberGenerator;
import lotto.domain.User;


import static lotto.view.OutputView.printLottoMoneyCount;
import static lotto.view.OutputView.printResult;

public class LottoGame {
    public enum RANKING {
        RANK1(200000000),
        RANK2(30000000),
        RANK3(1500000),
        RANK4(50000),
        RANK5(5000),
        RANK6( 0);

        private int winningMoney;
        RANKING(int winningMoney) {
            this.winningMoney = winningMoney;
        }
    }
    private static final LottoMachine lottoMachine = new LottoMachine(new LottoRandomNumberGenerator());
    private static User user = new User();
    private static List<Lotto> lottoCount = new ArrayList<>();
    private static final int RANK_SIZE = 6;
    private static int [] countRank;
    private int revenue;
    public void run() {
        init();
        gameStart();
        compare();
        printResult(revenue, countRank, user.getMoney());
    }
    private void init() {
        user.getInputMoney();
        lottoCount = lottoMachine.makeNumber(user.getMoney());
        printLottoMoneyCount(lottoCount);
    }
    private void gameStart() {
        user.inputWinningNumber();
        user.inputBonusNumber();
    }
    private void compare() {
        countRank = new int[RANK_SIZE];
        for(Lotto lotto : lottoCount) {
            List<Integer> numbers = new ArrayList<>(lotto.getNumbers());
            int rank = getRank(user.getWinningNumber(), user.getBonusNumber(), numbers);
            if(rank > 5) {
                continue;
            }
            countRank[rank] += 1;
        }
        calculateRevenue();
    }
    private int getRank(List<Integer> winningNumber, int bonusNumber, List<Integer> numbers) {
        boolean bonusNumIsOk = numbers.contains(bonusNumber);
        numbers.retainAll(winningNumber);
        // numbers.size() = 일치한 갯수
        int rank = 8 - numbers.size();

        if(numbers.size() == 5 && bonusNumIsOk) {
            rank -= 1; // 8-5-1=6
        }
        if(numbers.size() == 6) {
            rank = 1;
        }
        return rank;
    }
    private void calculateRevenue() {
        revenue = 0;
        for(int i = 1; i < countRank.length; i++) {
            long reward = RANKING.valueOf("RANK"+i).winningMoney;
            revenue += reward / countRank[i];
        }
    }
}
