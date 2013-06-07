package pkm.monster;

import pkm.Player;
import pkm.move.MoveTackle;

public class PokemonBulbasaur extends Pokemon{
	public PokemonBulbasaur(Player owner, int HP){
		super(owner, HP);
	}

	@Override
	void setMoves() {
		this.move[0] = new MoveTackle(this);
	}

	@Override
	void setStats(int HP, String name, Player owner) {
		this.HP = HP;
		this.name = name;
		this.owner = owner;
	}
}
