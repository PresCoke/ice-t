package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Stats Class
 * @author TimHP
 *
 */
@Entity
@Table(name="Stats")
public class Stats {

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

}
