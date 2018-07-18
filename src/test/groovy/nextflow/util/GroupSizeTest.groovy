/*
 * Copyright (c) 2013-2018, Centre for Genomic Regulation (CRG).
 * Copyright (c) 2013-2018, Paolo Di Tommaso and the respective authors.
 *
 *   This file is part of 'Nextflow'.
 *
 *   Nextflow is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Nextflow is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Nextflow.  If not, see <http://www.gnu.org/licenses/>.
 */

package nextflow.util

import spock.lang.Specification

/**
 *
 * @author Paolo Di Tommaso <paolo.ditommaso@gmail.com>
 */
class GroupSizeTest extends Specification {

    def 'should mimic target object' () {
        given:
        def hello = 'Hello world!'
        when:
        def group = new GroupSize(hello, 10)

        then:
        group.size() == hello.size()
        group == hello
        group.equals(hello)
        group.hashCode() == hello.hashCode()
        group.groupSize == 10

    }

    def 'should verify equals and hashCode' () {
        given:
        def hello = 'Hello world!'
        when:
        def g1 = new GroupSize(hello, 10)
        def g2 = new GroupSize(hello, 10)
        def g3 = new GroupSize(hello.reverse(), 10)

        then:
        g1 == g2
        g1.equals(g2)
        
        g1 != g3
        !g1.equals(g3)

        g1.hashCode() == g2.hashCode()
        g1.hashCode() != g3.hashCode()

    }


}
