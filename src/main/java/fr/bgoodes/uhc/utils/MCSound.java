package fr.bgoodes.uhc.utils;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class MCSound {
	
	private Sound sound;
	private String soundPath;
	
	private final float volume;
	private final float pitch;
	
	private static final float DEFAULT_VOLUME = 1f;
	private static final float DEFAULT_PITCH = 1f;
	
	public MCSound(Sound sound) {
		this(sound, DEFAULT_VOLUME);
	}
	
	public MCSound(Sound sound, float volume) {
		this(sound, volume, DEFAULT_PITCH);
	}
	
	public MCSound(Sound sound, float volume, float pitch) {
		this.sound = sound;
		this.volume = volume;
		this.pitch = pitch;
	}
	
	public MCSound(CustomSounds sound) {
		this(sound.path);
	}
	
	public MCSound(String soundPath) {
		this(soundPath, DEFAULT_VOLUME);
	}
	
	public MCSound(CustomSounds sound, float volume) {
		this(sound.path, volume);
	}
	
	public MCSound(String soundPath, float volume) {
		this(soundPath, volume, DEFAULT_PITCH);
	}
	
	public MCSound(CustomSounds sound, float volume, float pitch) {
		this(sound.path, volume, pitch);
	}
	
	public MCSound(String soundPath, float volume, float pitch) {
		this.soundPath = soundPath;
		this.volume = volume;
		this.pitch = pitch;
	}
	
	public void play(Player player) {
		if (soundPath != null) player.playSound(player.getLocation(), soundPath, volume, pitch);
		else player.playSound(player, sound, volume, pitch);
	}
	
	public void play(Player player, Location location) {
		if (soundPath != null) player.playSound(location, soundPath, volume, pitch);
		else player.playSound(location, sound, volume, pitch);
	}
	
	public void play(Location location) {
		location.getWorld().playSound(location, sound, volume, pitch);
	}
	
	public enum CustomSounds {
		HUNGER_GAMES("uhc:hunger_games");
		
		private final String path;

		CustomSounds(String path) {
			this.path = path;
		}
		
		public String path() {
			return path;
		}
	}
}
