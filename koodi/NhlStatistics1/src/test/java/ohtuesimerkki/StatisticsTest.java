package ohtuesimerkki;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;



public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            List<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));  //16
            players.add(new Player("Lemieux", "PIT", 45, 54)); //99
            players.add(new Player("Kurri",   "EDM", 37, 53)); //90
            players.add(new Player("Yzerman", "DET", 42, 56)); //98
            players.add(new Player("Gretzky", "EDM", 35, 89)); //124

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void invalidPlayerSearchReturnsNull() {
        assertEquals(null, stats.search("asd"));
    }

    @Test
    public void validPlayerSearchReturnsPlayer() {
        Player player = stats.search("Kurri");

        assertEquals("Kurri", player.getName());
    }

    @Test
    public void invalidTeamSearchReturnsEmptyList() {
        List<Player> team = stats.team("Mahtiankat");

        assertEquals(0, team.size());
    }

    @Test
    public void validTeamSearchReturnsPlayers() {
        List<String> team = stats.team("EDM")
            .stream()
            .map(p -> p.getName())
            .collect(Collectors.toList());

        List<String> expected = new ArrayList<>();
        expected.add("Semenko");
        expected.add("Kurri");
        expected.add("Gretzky");

        assertThat(team, is(expected));
    }

    @Test
    public void topScoresAreSortedCorrectly() {
        List<String> top = stats.topScorers(4)
            .stream()
            .map(p -> p.getName())
            .collect(Collectors.toList());

        List<String> expected = new ArrayList<>();
        expected.add("Gretzky");
        expected.add("Lemieux");
        expected.add("Yzerman");
        expected.add("Kurri");
        expected.add("Semenko");

        assertThat(top, is(expected));
    }
}