/**
 * 
 */
package com.petrel.vo;

import javax.xml.bind.annotation.XmlElement;

public class VoiceResponse {

	private String MediaId;

	@XmlElement(name="MediaId")
	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
	
}
