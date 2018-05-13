// Name: Vadim Kobrinsky
// ID: 314257551

package hw2_VadimKobrinsky;

public interface InstrumentFunc extends Cloneable, Comparable<MusicalInstrument> {
	
	@Override
	public int compareTo(MusicalInstrument instrument);

}
