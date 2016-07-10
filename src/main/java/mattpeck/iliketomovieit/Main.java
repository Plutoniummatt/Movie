package mattpeck.iliketomovieit;

import static java.lang.System.getProperty;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import mattpeck.iliketomovieit.http.HttpException;

/**
 * Application entrypoint
 */
public class Main {

	public static final Injector injector = Guice.createInjector(new Module() {
		@Override
		public void configure(final Binder binder) {
		}
	});

	public static void main(final String[] args) {
		final String title = getProperty("movie");
		final String api = getProperty("api");

		try {
			injector.getInstance(QueryApp.class).run(title, api);
		} catch (final HttpException e) {
			System.out.println("Error when attempting to pull information from external systems, check your connection");
		} catch (final ValidationException e) {
			// Handled already
		}
	}
}