package cl.yai.amandaia.web.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import com.tetradotoxina.gtts4j.GTTS4J;
import com.tetradotoxina.gtts4j.exception.GTTS4JException;
import com.tetradotoxina.gtts4j.impl.GTTS4JImpl;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public final class TextToSpeechTest {

	private static Logger log = LoggerFactory.getLogger(OpenNLPTest.class);

	@Test
	@Order(1)
	public void engTest() {
		log.info("Eng TextToSpeeech");
		GTTS4J gtts4j = new GTTS4JImpl();
		String text = "Hellow Edgar you are my master";
		String lang = "en";
		boolean slow = false;
		String filePath = System.getProperty("user.dir") + File.separator + "demo.mp3";

		byte[] data;
		try {
			data = gtts4j.textToSpeech(text, lang, slow);
			gtts4j.saveFile(filePath, data, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private InputStream toInputStream(byte[] bytes) {
		InputStream targetStream = new ByteArrayInputStream(bytes);
		return targetStream;
	}
	//http://chuwiki.chuidiang.org/index.php?title=Reproducir_MP3_con_Java
	@Test
	@Order(2)
	public void espTest() {
		log.info("Esp TextToSpeeech");
		GTTS4J gtts4j = new GTTS4JImpl();
		String text = "Hola Raúl Andrés. ¿Cómo estas?";
		String lang = "es";
		boolean slow = false;
		byte[] data;
		try {
			data = gtts4j.textToSpeech(text, lang, slow);
			Player apl = new Player(toInputStream(data));
			apl.play();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	@Order(3)
	public void speackTest() {
        try {
            
            Player apl = new Player(new FileInputStream("demo.mp3"));

              apl.play();
        } catch (Exception e) {
            System.out.println("" + e);
        }
	}	
}