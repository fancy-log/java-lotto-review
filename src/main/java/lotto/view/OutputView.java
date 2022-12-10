package lotto.view;

import java.util.Collections;
import java.util.List;

import lotto.domain.Lotto;

public class OutputView {
    public void printLottoMoneyCount(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }
    public void printEnter() {
        System.out.println();
    }
}