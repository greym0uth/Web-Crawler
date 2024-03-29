package edu.neumont.jeb.webcrawler;

import edu.neumont.jeb.storage.IStorable;

public class Word implements IStorable {

	private String url;
	private String word;
	private int occurances;

	public Word() {

	}

	public Word(String url, String word, int occurances) {
		this.setUrl(url);
		this.setWord(word);
		this.setOccurances(occurances);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getOccurances() {
		return occurances;
	}

	public void setOccurances(int occurances) {
		this.occurances = occurances;
	}

	public void pushOccurances() {
		this.setOccurances(this.getOccurances() + 1);
	}

	@Override
	public String serialize() {
		return String.format("%255s%255s%10s", this.getUrl(), this.getWord(), this.getOccurances());
	}

	@Override
	public void deserialize(String data) {
		String url = data.substring(0, 255).trim();
		String word = data.substring(255, 255 * 2).trim();
		int occurances = Integer.parseInt(data.substring(255 * 2, 255 * 2 + 10).trim());

		this.setUrl(url);
		this.setWord(word);
		this.setOccurances(occurances);
	}

	@Override
	public int sizeOf() {
		return 255 + 255 + 10;
	}

	@Override
	public String getKey() {
		return this.getWord(); 
	}

	@Override
	public String toString() {
		return "Word{" +
				"url='" + this.getUrl() + '\'' +
				", word='" + this.getWord() + '\'' +
				", occurances=" + this.getOccurances() +
				'}';
	}
}
