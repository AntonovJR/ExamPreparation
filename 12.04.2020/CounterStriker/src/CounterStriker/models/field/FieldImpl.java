package CounterStriker.models.field;

import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;

import java.util.Collection;
import java.util.stream.Collectors;

import static CounterStriker.common.OutputMessages.*;

public class FieldImpl implements Field {
    @Override
    public String start(Collection<Player> players) {
        Collection<Player> contraTerrorists = players.stream()
                .filter(p -> p instanceof CounterTerrorist)
                .collect(Collectors.toList());
        Collection<Player> terrorists = players.stream()
                .filter(p -> p instanceof Terrorist)
                .collect(Collectors.toList());

        while (contraTerrorists.stream().anyMatch(Player::isAlive)
                && terrorists.stream().anyMatch(Player::isAlive)) {
            for (Player terrorist : terrorists) {
                for (Player contraTerrorist : contraTerrorists) {
                    contraTerrorist.takeDamage(terrorist.getGun().fire());
                }
            }
            contraTerrorists = contraTerrorists.stream()
                    .filter(Player::isAlive).collect(Collectors.toList());

            for (Player contraTerrorist : contraTerrorists) {
                for (Player terrorist : terrorists) {
                    terrorist.takeDamage(contraTerrorist.getGun().fire());
                }
            }
            terrorists = terrorists.stream()
                    .filter(Player::isAlive).collect(Collectors.toList());



        }
        return contraTerrorists.stream().anyMatch(Player::isAlive)
                ? COUNTER_TERRORIST_WINS : TERRORIST_WINS;
    }
}
