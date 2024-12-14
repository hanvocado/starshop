package com.starshop.models;

import com.starshop.utils.WSAction;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WSConnectMessage {
	private String user;
	
	private WSAction action;
	
}
