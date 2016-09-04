package fr.aleclerc.rasp.music.storage.utils;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import fr.aleclerc.rasp.music.storage.exception.StorageException;

public class ImageUtils {

	private static byte[] noCover = null;

	public byte[] scale(byte[] fileData, int width, int height) throws StorageException {
		ByteArrayInputStream in = new ByteArrayInputStream(fileData);
		try {
			BufferedImage img = ImageIO.read(in);
			if (height == 0) {
				height = (width * img.getHeight()) / img.getWidth();
			}
			if (width == 0) {
				width = (height * img.getWidth()) / img.getHeight();
			}

			Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);

			ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			ImageIO.write(imageBuff, "jpg", buffer);

			return buffer.toByteArray();
		} catch (IOException e) {
			throw new StorageException("IOException in scale");
		}
	}

	public byte[] getNoCover() throws StorageException {
		if (noCover == null) {
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("no-cover.jpg");
			noCover = this.scale(this.isToByteArray(is), 80, 80);
		}
		return noCover;
	}

	private byte[] isToByteArray(InputStream is) throws StorageException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		try {
			int nRead;
			byte[] data = new byte[16384];

			while ((nRead = is.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}

			buffer.flush();
		} catch (IOException e) {
			throw new StorageException(e);
		}
		return buffer.toByteArray();
	}
}
