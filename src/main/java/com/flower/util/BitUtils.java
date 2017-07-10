package com.flower.util;

public class BitUtils {

    /**
     * ��ȡ������ָ��λ�õ�ֵ<br>
     * ���磺 0000 1011 ��ȡ��� 0 λ��ֵΪ 1, �� 2 λ ��ֵΪ 0<br>
     * 
     * @param source
     *            ��Ҫ�������
     * @param pos
     *            ָ��λ�� (0<=pos<=7)
     * @return ָ��λ�õ�ֵ(0 or 1)
     */
    public static byte getBitValue(byte source, int pos) {
        return (byte) ((source >> pos) & 1);
    }

    /**
     * ��������ָ��λ�õ�ֵ��Ϊָ��ֵ<br>
     * ��: 0000 1011 ��Ҫ����Ϊ 0000 1111, ���� 2 λ��ֵ��Ҫ��Ϊ 1<br>
     * 
     * @param source
     *            ��Ҫ�������
     * @param pos
     *            ָ��λ�� (0<=pos<=7)
     * @param value
     *            ֻ��ȡֵΪ 0, �� 1, ���д���0��ֵ��Ϊ1����, ����С��0��ֵ��Ϊ0����
     * 
     * @return �����Ľ����
     */
    public static byte setBitValue(byte source, int pos, byte value) {

        byte mask = (byte) (1 << pos);
        if (value > 0) {
            source |= mask;
        } else {
            source &= (~mask);
        }

        return source;
    }

    /**
     * ��������ָ��λ��ȡ��ֵ<br>
     * ���� 0000 1011 ָ���� 3 λȡ��, ���Ϊ 0000 0011; ָ����2λȡ��, ���Ϊ 0000 1111<br>
     * 
     * @param source
     * 
     * @param pos
     *            ָ��λ�� (0<=pos<=7)
     * 
     * @return �����Ľ����
     */
    public static byte reverseBitValue(byte source, int pos) {
        byte mask = (byte) (1 << pos);
        return (byte) (source ^ mask);
    }

    /**
     * �����������ָ��λ���Ƿ�Ϊ1<br>
     * 
     * @param source
     *            ��Ҫ�������
     * @param pos
     *            ָ��λ�� (0<=pos<=7)
     * @return true ��ʾָ��λ��ֵΪ1, false ��ʾָ��λ��ֵΪ 0
     */
    public static boolean checkBitValue(byte source, int pos) {

        source = (byte) (source >>> pos);

        return (source & 1) == 1;
    }

    /**
     * ��ں���������<br>
     * 
     * @param args
     */
    public static void main(String[] args) {

        // ȡʮ���� 11 (������ 0000 1011) Ϊ����
        byte source = 11;

        // ȡ��2λֵ�����, ���ӦΪ 0000 1011
        for (byte i = 7; i >= 0; i--) {
            System.out.printf("%d ", getBitValue(source, i));
        }

        // ����6λ��Ϊ1����� , ���Ϊ 75 (0100 1011)
        System.out.println("\n" + setBitValue(source, 6, (byte) 1));

        // ����6λȡ�������, ���ӦΪ75(0100 1011)
        System.out.println(reverseBitValue(source, 6));

        // ����6λ�Ƿ�Ϊ1�����ӦΪfalse
        System.out.println(checkBitValue(source, 6));

        // ���Ϊ1��λ, ���ӦΪ 0 1 3
        for (byte i = 0; i < 8; i++) {
            if (checkBitValue(source, i)) {
                System.out.printf("%d ", i);
            }
        }

    }

}
