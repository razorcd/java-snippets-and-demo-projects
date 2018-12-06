package demo.stage1initial;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class RoverRepositoryTest {

    @Test
    public void persistRover() {
        Rover rover = new Rover("id-01", new Rover.Location(1,2), Rover.Orientation.N);

        RoverRepository roverRepository = new RoverRepository();
        roverRepository.save(rover);

        Rover savedRover = roverRepository.findById("id-01");
        assertThat(savedRover).isEqualTo(rover);
    }

    @Test
    public void overwritePersistedRover() {
        RoverRepository roverRepository = new RoverRepository();

        Rover rover = new Rover("id-01", new Rover.Location(1,2), Rover.Orientation.N);
        roverRepository.save(rover);

        rover.moveForward();
        roverRepository.save(rover);

        Rover savedRover = roverRepository.findById("id-01");
        assertThat(savedRover).isEqualTo(new Rover("id-01", new Rover.Location(2,2), Rover.Orientation.N));
    }
}