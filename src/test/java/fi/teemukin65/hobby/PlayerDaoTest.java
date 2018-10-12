package fi.teemukin65.hobby;


import fi.teemukin65.hobby.tarinapeli.config.PersistenceContextConfig;
import fi.teemukin65.hobby.tarinapeli.dao.PlayerRepository;
import fi.teemukin65.hobby.tarinapeli.domain.Player;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {
        PersistenceContextConfig.class
})
@Transactional
public class PlayerDaoTest {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    PlayerRepository playerRepository;


    @Before
    @Transactional()
    public void setupDataBase() throws Exception {

        Flyway flyway = new Flyway();
        LOGGER.info("setupDatabase, {}", dataSource.getConnection().getClientInfo());
        flyway.setDataSource(dataSource);
        flyway.migrate();
    }


    @Test
    @Transactional
    public void testExample() throws Exception {
        final Player player = new Player("testdao@example.org");
        this.entityManager.persist(player);
        this.entityManager.flush();
        Player storedPlayer = this.entityManager.find(Player.class, "testdao@example.org");
        assertEquals("One player persisted?", 1L, this.playerRepository.count());
        Iterable<Player> players = this.playerRepository.findAll();
        LOGGER.debug("Players: {}", players.spliterator().toString());
        Player firstPlayer = players.iterator().next();
        assertEquals("The first player is the only one stored",
                firstPlayer.getEmail(), storedPlayer.getEmail());
    }

}
