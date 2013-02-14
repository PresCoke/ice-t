package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Stats Class
 * @author TimHP
 *
 */
@Entity
@Table(name="Stats")
public class Stats {

    @Id
    @GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
    @Column(name="Stats_id")
    private int id;	
	
	@Column(name="kills")
	private int kills;
	@Column(name="deaths")
	private int deaths;
	@Column(name="hits")
	private int hits;
	@Column(name="misses")
	private int misses;
	@Column(name="assists")
	private int assists;
	
	//Associations
	@OneToOne
	@JoinColumn (name="Player_id")
	private Player player;
	
	/**
	 * Default constructor
	 */
	public Stats() {
	}

	
	/**
	 * Getters & Setters
	 */
	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public int getMisses() {
		return misses;
	}

	public void setMisses(int misses) {
		this.misses = misses;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
