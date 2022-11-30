package com.mobilestore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailInfo {
	private String from;
	private String to;
	private String subject;
	private String body;

	public MailInfo(String to, String subject, String body) {
		super();
		this.from = "Mobile Estore";
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
}
