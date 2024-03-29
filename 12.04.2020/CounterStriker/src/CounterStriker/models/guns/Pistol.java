package CounterStriker.models.guns;

public class Pistol extends GunImpl{
    private static final int BULLETS_TO_SHOOT = 1;
    public Pistol(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        if(super.getBulletsCount()<BULLETS_TO_SHOOT){
            return 0;
        }
        super.setBulletsCount(super.getBulletsCount()-1);
        return BULLETS_TO_SHOOT;
    }
}

