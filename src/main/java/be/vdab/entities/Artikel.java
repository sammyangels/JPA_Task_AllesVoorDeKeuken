package be.vdab.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "artikels")
public class Artikel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private long id;
    private String naam;
    private BigDecimal aankoopprijs;
    private BigDecimal verkoopprijs;

    protected Artikel() {}

    public Artikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs) {
        if (! isPrijsValid(aankoopprijs, verkoopprijs)) {
            throw new IllegalArgumentException();
        }
        setNaam(naam);
        setAankoopprijs(aankoopprijs);
        setVerkoopprijs(verkoopprijs);
    }

    public static boolean isNaamValid(String naam) {
        return naam != null && !naam.isEmpty();
    }

    public static boolean isAankoopprijsValid(BigDecimal aankoopprijs) {
        return aankoopprijs != null && aankoopprijs.compareTo(BigDecimal.ZERO) >= 0;
    }

    public static boolean isVerkoopprijsValid(BigDecimal verkoopprijs) {
        return verkoopprijs != null && verkoopprijs.compareTo(BigDecimal.ZERO) >= 0;
    }

    public static boolean isPrijsValid(BigDecimal aankoopprijs, BigDecimal verkoopprijs) {
        return aankoopprijs.compareTo(verkoopprijs) <= 0;
    }

    public void setNaam(String naam) {
        if (!isNaamValid(naam)) {
            throw new IllegalArgumentException();
        }
        this.naam = naam;
    }

    public void setAankoopprijs(BigDecimal aankoopprijs) {
        if (!isAankoopprijsValid(aankoopprijs)) {
            throw new IllegalArgumentException();
        }
        this.aankoopprijs = aankoopprijs;
    }

    public void setVerkoopprijs(BigDecimal verkoopprijs) {
        if (!isVerkoopprijsValid(verkoopprijs)) {
            throw new IllegalArgumentException();
        }
        this.verkoopprijs = verkoopprijs;
    }

    public BigDecimal getWinst() {
        return verkoopprijs.subtract(aankoopprijs).divide(aankoopprijs, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public BigDecimal getAankoopprijs() {
        return aankoopprijs;
    }

    public BigDecimal getVerkoopprijs() {
        return verkoopprijs;
    }
}
