package lotto.controller;

import lotto.util.Validate;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGame {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    Validate validate = new Validate();

    public void run() {
        int money = validate.checkInputMoney(inputView.readInputMoney());

    }
}
