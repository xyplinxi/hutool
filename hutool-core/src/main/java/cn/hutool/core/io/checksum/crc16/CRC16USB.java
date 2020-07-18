package cn.hutool.core.io.checksum.crc16;

/**
 * CRC16_USB：多项式x16+x15+x2+1（0x8005），初始值0xFFFF，低位在前，高位在后，结果与0xFFFF异或
 * 0xA001是0x8005按位颠倒后的结果
 *
 * @author looly
 * @since 5.3.10
 */
public class CRC16USB extends CRC16Checksum{

	private static final int wCPoly = 0xa001;

	public CRC16USB(){
		this.wCRCin = 0xFFFF;
	}

	@Override
	public void update(byte[] b, int off, int len) {
		super.update(b, off, len);
		wCRCin ^= 0xffff;
	}

	@Override
	public void update(int b) {
		wCRCin ^= (b & 0x00ff);
		for (int j = 0; j < 8; j++) {
			if ((wCRCin & 0x0001) != 0) {
				wCRCin >>= 1;
				wCRCin ^= wCPoly;
			} else {
				wCRCin >>= 1;
			}
		}
	}
}
