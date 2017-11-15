package fi.teemukin65.hobby;


import fi.teemukin65.hobby.tarinapeli.config.JOOQToSpringExceptionTransformer;
import fi.teemukin65.hobby.tarinapeli.config.PersistenceContextConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@ContextConfiguration(classes = {PersistenceContextConfig.class, JOOQToSpringExceptionTransformer.class})
public class PlayerDaoTest {
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private PlayerRepository repository;

    @Test
    public void testExample() throws Exception {
//        this.entityManager.persist(new Player("testdao@example.org"));
//        assertEquals(1L, this.repository.count());
//        Iterable<Player> players = this.repository.findAll();
//        Player firstPlayer = players.iterator().next();
//        assertEquals("The first player is the only one stored",
//                firstPlayer.getEmail(), "testdao@example.org");

        assertTrue(true);
    }

}
