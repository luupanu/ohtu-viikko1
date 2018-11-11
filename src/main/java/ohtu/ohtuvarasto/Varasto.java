package ohtu.ohtuvarasto;

import java.lang.*;

public class Varasto {

    // --- piilotettu tietorakenteen toteutus: ---
    private double tilavuus;  // paljonko varastoon mahtuu,  > 0
    private double saldo;     // paljonko varastossa on nyt, >= 0

    // --- konstruktorit: ---
    public Varasto(double tilavuus) {
        this.tilavuus = tilavuus < 0.0 ? 0.0 : tilavuus;
        this.saldo = 0.0;
    }

    public Varasto(double tilavuus, double alkuSaldo) { // kuormitetaan
        this.tilavuus = tilavuus < 0.0 ? 0.0 : tilavuus;
        this.saldo = alkuSaldo < 0.0 ? 0.0 : Math.min(alkuSaldo, tilavuus);
    }

    // --- ottavat aksessorit eli getterit: ---
    public double getSaldo() {
        return this.saldo;
    }

    public double getTilavuus() {
        return this.tilavuus;
    }

    public double paljonkoMahtuu() {  // huom: ominaisuus voidaan myös laskea
        return this.tilavuus - this.saldo;        //  ei tarvita erillistä kenttää vielaTilaa tms.
    }

    // --- asettavat aksessorit eli setterit: ---
    public void lisaaVarastoon(double maara) {
        if (maara < 0) {
            return;
        }
        if (maara <= paljonkoMahtuu()) {
            this.saldo = this.saldo + maara;
        } else {
            this.saldo = this.tilavuus;
        }
    }

    public double otaVarastosta(double maara) {
        if (maara < 0) {
            return 0.0;
        }
        if (maara > this.saldo) {
            double kaikkiMitaVoidaan = this.saldo;
            this.saldo = 0.0;
            return kaikkiMitaVoidaan;
        }
        this.saldo = this.saldo - maara;
        return maara;
    }

    // --- Merkkijonoesitys Varasto-oliolle: ----
    public String toString() {
        return ("saldo = " + saldo + ", vielä tilaa " + paljonkoMahtuu());
    }
}