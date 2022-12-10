package lotto.util;

import java.util.List;

public class Validate {
    private static final String ERROR_MESSAGE = "[ERROR] : ";
    private static final int ZERO = 0;
    public int checkInputMoney(String input) {
        if (!input.matches("[0-9]+")) {
            throw new IllegalArgumentException(ERROR_MESSAGE + "숫자만 입력 가능합니다.");
        }
        int money = Integer.parseInt(input);
        if(money % 1000 != ZERO) {
            throw new IllegalArgumentException(ERROR_MESSAGE + "1,000원 단위로 입력만 가능합니다.");

        }
        return money;
    }
}
