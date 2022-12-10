package lotto.domain;

import static lotto.util.Validate.*;
import static lotto.view.InputView.*;
import static lotto.view.OutputView.*;

import java.util.ArrayList;
import java.util.List;

public class User {
    private static int money;
    private static List<Integer> winningNumber = new ArrayList<>();
    private static int bonusNumber;
    public void getInputMoney() {
        money = checkInputMoney(readInputMoney());
        printEnter();
    }
    public int getMoney() {
        return money;
    }
    public void inputWinningNumber() {
        String winningInput = readInputWinningNumber();
        List<String> numbers = List.of(winningInput.split(","));
        checkWinningNumberInfo(numbers);
        for(String str : numbers) {
            winningNumber.add(Integer.parseInt(str));
        }
        printEnter();
    }
    public List<Integer> getWinningNumber() {
        return winningNumber;
    }
    public void inputBonusNumber() {
        String bonusInput = readInputBonusNumber();
        checkBonusNumberInfo(bonusInput, winningNumber);
        bonusNumber = Integer.parseInt(bonusInput);
        printEnter();
    }
    public int getBonusNumber() {
        return bonusNumber;
    }
}
