package ch.hsr.geohash;

import java.util.Random;

import ch.mollusca.benchmarking.Before;
import ch.mollusca.benchmarking.Benchmark;

public class GeoHashEncodingBenchmark {
	private static final int NUMBER_OF_HASHES = 1000000;
	private GeoHash[] hashes;
	private double[] latitudes;
	private double[] longitudes;

	@Before
	public void setupBenchmark() {
		hashes = new GeoHash[NUMBER_OF_HASHES];
		latitudes = new double[NUMBER_OF_HASHES];
		longitudes = new double[NUMBER_OF_HASHES];

		Random rand = new Random();
		for (int i = 0; i < NUMBER_OF_HASHES; i++) {
			latitudes[i] = rand.nextDouble() * 180 - 90;
			longitudes[i] = rand.nextDouble() * 360 - 180;
		}
	}

	@Benchmark(times = 20)
	public void benchmarkGeoHashEncoding() {
		for (int i = 0; i < NUMBER_OF_HASHES; i++) {
			hashes[i] = GeoHash.withBitPrecision(latitudes[i], longitudes[i], 60);
		}
	}
}
