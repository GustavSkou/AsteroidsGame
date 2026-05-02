package dk.sdu.mmmi.cbse.score;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ScoreApplication {

	private static long highScore = 0;
	private static long score = 0;

	public static void main(String[] args) {
		SpringApplication.run(ScoreApplication.class, args);
	}

	@GetMapping("/score")
	public long getScore() {
		return score;
	}

	@GetMapping("/highscore")
	public long getScore() {
		return score;
	}

	@PostMapping("/score")
	public long addPoints(@RequestParam(value = "point", defaultValue = "0") long point) {
		score = score + point;
		if (score > highScore) {
			highScore = score;
		}
		return score;
	}

	@PostMapping("/reset")
	public long resetPoints(@RequestParam(value = "point", defaultValue = "0") long point) {
		score = 0;
		return score;
	}
}