/*
@authors: BurakYuksek - SametAltuntas
*/

import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                KutuphaneAnaSayfa kutuphaneAnaSayfa = new KutuphaneAnaSayfa();
                kutuphaneAnaSayfa.setVisible(true);   //Program ilk çalıştırıldığında Giriş Yap sayfasının açılışıyla başlar
            }
        });

    }
}
