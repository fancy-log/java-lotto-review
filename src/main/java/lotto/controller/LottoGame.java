package lotto.controller;

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

    public void run() {
        int money = validate.checkInputMoney(inputView.readInputMoney());
        List<Lotto> lottoMoneyCount = lottoMachine.makeNumber(money);
    }
}
