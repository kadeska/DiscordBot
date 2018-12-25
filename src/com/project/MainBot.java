package com.project;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;
import sx.blah.discord.util.RequestBuilder;

public class MainBot {
	private static ClientBuilder clientBuilder;
	private static final String token = "NDE1MjUzNzI3MTYzNzExNDg5.DwMe4Q.V909YiWFM4LwTLwk-Xu5nA5omF0";

	public static void main(String[] args) {
		/*
		 * if(args.length != 1){ System.out.
		 * println("Please enter the bots token as the first argument e.g java -jar thisjar.jar tokenhere"
		 * ); return; }
		 */
		createClient(token, true);
		//clientBuilder.login();
		IDiscordClient client = BotUtils.getBuiltDiscordClient(token);

		client.getDispatcher().registerListener(new MyEvents());

		/*
		RequestBuffer.request(() -> {
			// Ratelimited methods go here
			client.login();
		});
		*/
		 

		/*RequestBuilder builder = new RequestBuilder(client);
		builder.shouldBufferRequests(true);
		builder.doAction(() -> {
			client.login();
			return true;
		}).execute();*/

	}

	public static IDiscordClient createClient(String token, boolean login) { // Returns a new instance of the Discord
																				// client
		clientBuilder = new ClientBuilder(); // Creates the ClientBuilder instance
		clientBuilder.withToken(token); // Adds the login info to the builder
		try {
			if (login) {
				return clientBuilder.login(); // Creates the client instance and logs the client in
			} else {
				return clientBuilder.build(); // Creates the client instance but it doesn't log the client in yet, you
												// would have to call client.login() yourself
			}
		} catch (DiscordException e) { // This is thrown if there was a problem building the client
			e.printStackTrace();
			return null;
		}
	}
}
