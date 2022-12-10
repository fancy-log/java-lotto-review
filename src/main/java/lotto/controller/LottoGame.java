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

public class LottoGame {
    LottoMachine lottoMachine = new LottoMachine(new LottoRandomNumberGenerator());
    List<Integer> winningNumber = new ArrayList<>();
    public void run() {
        init();
        gameStart();
    }
    private void init() {
        int money = checkInputMoney(readInputMoney());
        printEnter();
        List<Lotto> lottoCount = lottoMachine.makeNumber(money);
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
        int bonusNumber = Integer.parseInt(bonusInput);
        printEnter();
    }
}
