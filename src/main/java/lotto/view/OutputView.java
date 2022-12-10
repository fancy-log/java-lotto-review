package lotto.view;

import java.util.List;

import lotto.domain.Lotto;

public class OutputView {
    public static void printLottoMoneyCount(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }
    public static void printEnter() {
        System.out.println();
    }
}