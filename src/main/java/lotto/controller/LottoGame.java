package lotto.controller;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoRandomNumberGenerator;

import static lotto.util.Validate.checkInputMoney;
import static lotto.util.Validate.checkWinningNumberInfo;
import static lotto.util.Validate.checkBonusNumberInfo;
import static lotto.view.InputView.readInputMoney;
import static lotto.view.InputView.readInputWinningNumber;
import static lotto.view.InputView.readInputBonusNumber;
import static lotto.view.OutputView.printLottoMoneyCount;
import static lotto.view.OutputView.printEnter;
import static lotto.view.OutputView.printResult;

public class LottoGame {
    public enum Rank {
        RANK1(6, 200000000),
        RANK2(5, 30000000),
        RANK3(5, 1500000),
        RANK4(4, 50000),
        RANK5(3, 5000),
        RANK6(0, 0);

        private int countOfMatch;
        private int winningMoney;
        Rank(int countOfMatch, int winningMoney) {
            this.countOfMatch = countOfMatch;
            this.winningMoney = winningMoney;
        }
    }
    private static final LottoMachine lottoMachine = new LottoMachine(new LottoRandomNumberGenerator());
    private static List<Lotto> lottoCount = new ArrayList<>();
    private static List<Integer> winningNumber = new ArrayList<>();
    private static final int RANK_SIZE = 6;
    private static int bonusNumber;
    private static int [] countRank;
    private int revenue;
    public void run() {
        init();
        gameStart();
        compare();
        printResult(t);
    }
    private void init() {
        int money = checkInputMoney(readInputMoney());
        printEnter();
        lottoCount = lottoMachine.makeNumber(money);
        printLottoMoneyCount(lottoCount);
        printEnter();
    }
    private void gameStart() {
        String winningInput = readInputWinningNumber();
        List<String> numbers = List.of(winningInput.split(","));
        checkWinningNumberInfo(numbers);
        for(String str : numbers) {
            winningNumber.add(Integer.parseInt(str));
        }
        printEnter();
        String bonusInput = readInputBonusNumber();
        checkBonusNumberInfo(bonusInput, winningNumber);
        bonusNumber = Integer.parseInt(bonusInput);
        printEnter();
    }
    private void compare() {
        countRank = new int[RANK_SIZE];
        for(Lotto lotto : lottoCount) {
            List<Integer> numbers = new ArrayList<>(lotto.getNumbers());
            int rank = getRank(winningNumber, bonusNumber, numbers);
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
        for(int i = 0; i < countRank.length; i++) {
            long reward = Rank.valueOf("RANK"+i).winningMoney;
            revenue += reward / countRank[i];
        }
    }
}
