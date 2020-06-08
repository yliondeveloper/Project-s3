package xyz.starmc.api;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;

public class DiscordGroup {
	
	public static String getGroup(Member member) {
		String index = "1";
		for(Role role : member.getRoles()) {
			index = index + " " + role.getName();
		}
		index = index.replace("1 ", "");
		if(index.contains("CEO")) {
			return "CEO";
		} else if(index.contains("DONO")) {
			return "DONO";
		} else if(index.contains("SUBDONO")) {
			return "SUBDONO";
		} else if(index.contains("DIRETOR")) {
			return "DIRETOR";
		} else if(index.contains("COORDENADOR")) {
			return "COORDENADOR";
		} else if(index.contains("GERENTEGC")) {
			return "GERENTEGC";
		} else if(index.contains("GERENTE")) {
			return "GERENTE";
		} else if(index.contains("ADMINGC")) {
			return "ADMINGC";
		} else if(index.contains("ADMIN")) {
			return "ADMIN";
		} else if(index.contains("MODGC")) {
			return "MODGC";
		} else if(index.contains("MOD")) {
			return "MOD";
		} else if(index.contains("TRIALGC")) {
			return "TRIALGC";
		} else if(index.contains("TRIAL")) {
			return "TRIAL";
		} else if(index.contains("BUILDER")) {
			return "BUILDER";
		} else if(index.contains("HELPER")) {
			return "HELPER";
		} else if(index.contains("HELPER+")) {
			return "HELPER+";
		} else {
			return "MEMBRO";
		}
	} 

}
