package xyz.starmc.api;

import java.awt.Color;

import net.dv8tion.jda.core.EmbedBuilder;

public class DiscordMessage {

	private static String[] mensagem = {
			"Utilize o comando !regras no chat #comandos e fique por dentro das regras do servidor.",
			"Você pode ajudar nosso servidor votando, para votar acesse o site: SITE DO VOTE, votando você receberá uma recompensa de 300 de XP, podendo votar novamente a cada dia e recebendo a recompensa também diariamente.",
			"Este bot foi desenvolvido por yLionCodes.",
			"Para se aplicar a nossa equipe use !aplicar e preencha o formúlario desejado.",
			"VIPS: \nNossa rede possui 3 tipos de grupos VIP, sendo eles: \nMarry, Ultimate e Crystal em uma ordem de menor para maior. \nOs três possuem vantagens em toda nossa rede, o menor possui menos vantagens e os maiores possuem mais. \nA tabela de valores dos mesmos são: \nULTRA:  15 dias (R$6,00) | 30 dias (R$15,00) | 60 dias (R$29,00) | Eterno (R$50,00)! \nULTIMATE:  15 dias (R$12,00) | 30 dias (R$25,00) | 60 dias (R$45,00) | Eterno (R$75,00)! \nCRYSTAL:  15 dias (R$18,00) | 30 dias (R$35,00) | 60 dias (R$75,00) | Eterno (R$110,00)! \nAdquirindo algum de nossos de VIPS além de receber vantagens você ajuda nosso servidor a crescer e a se manter online. \nAcesse: loja.pvp.cu e adquira já!",
			"Nos siga no twitter: @StarMC_",
			"Você pode requisitar suporte ou esclarecer dúvidas enviando um e-mail para:",
			"Acesse nosso site: https://starpvp.com.br/", "Acesse nossa loja: https://loja.starpvp.com.br/" };

	public static void activeAPI() throws InterruptedException {
		Thread.sleep(960000);
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("StarMC - AutoMessage");
		embed.setColor(Color.green);
		embed.setDescription((mensagem[new java.util.Random().nextInt(mensagem.length)]));
		JavaPlugin.getInstance().getAPI().getTextChannelById("547755752748613642").sendMessage(embed.build()).queue();
		JavaPlugin.getInstance().getAPI().getTextChannelById("546677367247208449").sendMessage(embed.build()).queue();
		JavaPlugin.getInstance().getAPI().getTextChannelById("546503697346199552").sendMessage(embed.build()).queue();
		JavaPlugin.getInstance().getAPI().getTextChannelById("546527175575207947").sendMessage(embed.build()).queue();
		JavaPlugin.getInstance().getAPI().getTextChannelById("507676231349698581").sendMessage(embed.build()).queue();
		JavaPlugin.getInstance().getAPI().getTextChannelById("545381892619370517").sendMessage(embed.build()).queue();
		activeAPI();
	}

}
