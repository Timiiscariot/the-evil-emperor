package theevilemperor;

public abstract class Character {


        //variables/attributes all characters have
        public String name;
        public int maxHp, hp, xp;

        //Constructor for character

        public Character(String name, int maxHp, int xp) {
            this.name = name;
            this.maxHp = maxHp;
            this.xp = xp;
        }

        //methods every characters have
        public abstract int attack();
        public abstract int defend();

    }
