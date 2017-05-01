package model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**

 */
@Entity
@Table(name = "GAME", schema = "sr03_web_project")
public class Game implements Serializable {

    private static final String ERROR_BINDING_GAME_TO_PUBLISHER = "A Game must be bind to a publisher";

    private int gameId;
    private String gameName;
    private int gameReleaseYear;
    private String gameImg;
    private boolean gameIsOnSale = false;
    private BigDecimal gameSaleRate = new BigDecimal(1);
    private boolean gameIsBest = false;
    private boolean gameIsNew = false;
    private boolean gameIsHot = false;
    private Date gameAddTime = new Date();
    private Set<Category> categories = new HashSet<>();
    private Set<PhysicalGame> physicalGames = new HashSet<>();
    private Publisher publisher;
    public Game() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id", unique = true, nullable = false)
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    @Basic
    @Column(name = "game_name", nullable = false, length = 50)
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Basic
    @Column(name = "game_release_year")
    public int getGameReleaseYear() {
        return gameReleaseYear;
    }

    public void setGameReleaseYear(int gameReleaseYear) {
        this.gameReleaseYear = gameReleaseYear;
    }

    @Basic
    @Column(name = "game_img", length = -1)
    public String getGameImg() {
        return gameImg;
    }

    public void setGameImg(String gameImg) {
        this.gameImg = gameImg;
    }

    @Basic
    @Column(name = "game_is_on_sale", nullable = false)
    public boolean isGameIsOnSale() {
        return gameIsOnSale;
    }

    public void setGameIsOnSale(boolean gameIsOnSale) {
        this.gameIsOnSale = gameIsOnSale;
    }

    @Basic
    @Column(name = "game_sale_rate", nullable = false, precision = 2)
    public BigDecimal getGameSaleRate() {
        return gameSaleRate;
    }

    public void setGameSaleRate(BigDecimal gameSaleRate) {
        this.gameSaleRate = gameSaleRate;
    }

    @Basic
    @Column(name = "game_is_best", nullable = false)
    public boolean isGameIsBest() {
        return gameIsBest;
    }

    public void setGameIsBest(boolean gameIsBest) {
        this.gameIsBest = gameIsBest;
    }

    @Basic
    @Column(name = "game_is_new", nullable = false)
    public boolean isGameIsNew() {
        return gameIsNew;
    }

    public void setGameIsNew(boolean gameIsNew) {
        this.gameIsNew = gameIsNew;
    }

    @Basic
    @Column(name = "game_is_hot", nullable = false)
    public boolean isGameIsHot() {
        return gameIsHot;
    }

    public void setGameIsHot(boolean gameIsHot) {
        this.gameIsHot = gameIsHot;
    }

    @Basic
    @Column(name = "game_add_time", nullable = false)
    public Date getGameAddTime() {
        return gameAddTime;
    }

    public void setGameAddTime(Date gameAddTime) {
        this.gameAddTime = gameAddTime;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    //@Cascade(CascadeType.SAVE_UPDATE)
    @JoinTable(name = "CATEGORY_GAME", schema = "sr03_web_project",
            joinColumns = {@JoinColumn(name = "game_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "cat_id", nullable = false, updatable = false)}
    )
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    @Cascade(CascadeType.SAVE_UPDATE) // when a game is created or updated, all items in Set<PhysicalGame> physicalGames will be saved/updated if need be, provided that the physical games do reference the game in their Game attribute
    public Set<PhysicalGame> getPhysicalGames() {
        return physicalGames;
    }

    public void setPhysicalGames(Set<PhysicalGame> physicalGames) {
        this.physicalGames = physicalGames;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_publisher_id", nullable = false)
    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game that = (Game) o;

        checkConsistency();
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(gameName, that.gameName);
        eb.append(publisher, that.publisher);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        checkConsistency();
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(gameName);
        hcb.append(publisher);
        return hcb.toHashCode();
    }

    public void addPhysicalGame(PhysicalGame physicalGame) {
        if (physicalGames == null)
            physicalGames = new HashSet<>();

        physicalGames.add(physicalGame);
    }

    public void addCategory(Category category) {
        if (categories == null)
            categories = new HashSet<>();
        categories.add(category);
    }

    private void checkConsistency() {
        if (this.publisher == null)
            throw new ModelException(ERROR_BINDING_GAME_TO_PUBLISHER);
    }
}
