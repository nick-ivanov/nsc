/*
    Network Signal Coder -- a utility that converts binary
        sequences into standard network physical signals.

    Copyright (C) 2015-2017  Nick Ivanov <nnrowan@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package nsc;

import javafx.scene.control.Spinner;

public class SignalProcessor {
    private int numberOfBits;
    private SignalQuantumImage[][] qim;
    private Spinner<Integer> prev;
    private Spinner<Integer>[] sp;
    private int[] iMsg;

    SignalProcessor(int numberOfBits, SignalQuantumImage[][] qim, int[] iMsg, Spinner<Integer> prev, Spinner<Integer>[] sp) {
        this.numberOfBits = numberOfBits;
        this.qim = qim;
        this.prev = prev;
        this.sp = sp;
        this.iMsg = iMsg;
    }

    public void processNRZL()
    {
        int a, p;

        p = prev.getValue();

        for(int i = 0; i < numberOfBits; i++) {
            a = iMsg[i];

            if(a == 0) {
                if(p == 0) { qim[0][i].drawQuantum("79"); }
                else { qim[0][i].drawQuantum("179"); }
            } else {
                if(p == 0) { qim[0][i].drawQuantum("713"); }
                else { qim[0][i].drawQuantum("13"); }
            }

            p = iMsg[i];
        }
    }

    public void processNRZLPolar()
    {
        int a, p;

        p = prev.getValue();

        for(int i = 0; i < numberOfBits; i++) {
            a = sp[i].getValue();

            if(a == 0) {
                if(p == 0) { qim[1][i].drawQuantum("79"); }
                else { qim[1][i].drawQuantum("179"); }
            } else {
                if(p == 0) { qim[1][i].drawQuantum("713"); }
                else { qim[1][i].drawQuantum("13"); }
            }

            p = sp[i].getValue();
        }
    }

    public void processNRZI()
    {
        int a, p;

        p = prev.getValue();

        for(int i = 0; i < numberOfBits; i++) {
            a = sp[i].getValue();

            if(a == 1) {
                if(p == 1) { qim[2][i].drawQuantum("179"); p = 0; }
                else { qim[2][i].drawQuantum("713"); p = 1; }
            } else {
                if(p == 1) { qim[2][i].drawQuantum("13"); p = 1; }
                else { qim[2][i].drawQuantum("79"); p = 0; }
            }
        }
    }

    public void processBipolar()
    {
        int a, p, pp;

        p = prev.getValue();
        pp = -1;

        for(int i = 0; i < numberOfBits; i++) {
            a = sp[i].getValue();

            if(a == 0) {
                if(p == 1) { qim[3][i].drawQuantum("146"); p = 0; }
                else if(p == -1) { qim[3][i].drawQuantum("746"); p = 0; }
                else { qim[3][i].drawQuantum("46"); p = 0; }
            } else { // a == 1
                if(pp == -1) {
                    if(p == 0) { qim[3][i].drawQuantum("413"); p = 1; pp = 1; }
                    else { qim[3][i].drawQuantum("713"); p = 1; pp = 1; }
                } else {
                    if(p == 0) { qim[3][i].drawQuantum("479"); p = -1; pp = -1; }
                    else { qim[3][i].drawQuantum("179"); p = -1; pp = -1; }
                }
            }
        }
    }

    public void processManchester()
    {
        int a, p;

        p = prev.getValue();

        for(int i = 0; i < numberOfBits; i++) {
            a = sp[i].getValue();

            if(a == 0) {
                if(p == 1) { qim[4][i].drawQuantum("1289"); }
                else { qim[4][i].drawQuantum("71289"); }
            } else {
                if(p == 1) { qim[4][i].drawQuantum("17823"); }
                else { qim[4][i].drawQuantum("7823"); }
            }

            p = sp[i].getValue();
        }
    }

    public void processDManchester()
    {
        int a, p;

        p = prev.getValue();

        for(int i = 0; i < numberOfBits; i++) {
            a = sp[i].getValue();

            if(a == 0) {
                if(p == 1) { qim[5][i].drawQuantum("17823"); p = 1; }
                else { qim[5][i].drawQuantum("71289"); p = 0; }
            } else {
                if(p == 1) { qim[5][i].drawQuantum("1289"); p = 0; }
                else { qim[5][i].drawQuantum("7823"); p = 1; }
            }
        }
    }

}
