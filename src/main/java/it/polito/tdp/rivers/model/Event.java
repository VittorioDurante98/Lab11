package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event implements Comparable<Event>{

	public enum EventType{
		DAY
	}
	private LocalDate time;
	private EventType type;
	private Flow flusso;
	
	public Event(LocalDate time, EventType type, Flow flow) {
		this.time = time;
		this.type = type;
		this.flusso = flow;
	}
	
	public Flow getFlusso() {
		return flusso;
	}

	public void setFlow(Flow flow) {
		this.flusso = flow;
	}

	public LocalDate getTime() {
		return time;
	}
	public void setTime(LocalDate time) {
		this.time = time;
	}
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	@Override
	public int compareTo(Event o) {
		return this.time.compareTo(o.getTime());
	}
	
}
