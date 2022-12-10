package lotto.controller;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoRandomNumberGenerator;
import lotto.util.Validate;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGame {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    Validate validate = new Validate();
    LottoMachine lottoMachine = new LottoMachine(new LottoRandomNumberGenerator());
    List<Integer> winningNumber = new ArrayList<>();
    public void run() {
        init();
        gameStart();
    }
    private void init() {
        int money = validate.checkInputMoney(inputView.readInputMoney());
        outputView.printEnter();
        List<Lotto> lottoCount = lottoMachine.makeNumber(money);
        outputView.printLottoMoneyCount(lottoCount);
        outputView.printEnter();
    }
    private void gameStart() {
        String winningInput = inputView.readInputWinningNumber();
        List<String> numbers = List.of(winningInput.split(","));
        validate.checkWinningNumberInfo(numbers);
        for(String str : numbers) {
            winningNumber.add(Integer.parseInt(str));
        }
        outputView.printEnter();
        String bonusInput = inputView.readInputBonusNumber();
        validate.checkBonusNumberInfo(bonusInput, winningNumber);
        int bonusNumber = Integer.parseInt(bonusInput);
        outputView.printEnter();
    }
}
