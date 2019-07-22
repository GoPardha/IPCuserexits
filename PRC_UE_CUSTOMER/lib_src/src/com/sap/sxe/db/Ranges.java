/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.sxe.db;

import java.util.Vector;


/**Defines the valid ranges (values) for an attribute (table column).
 * A Ranges instance is defined by a set of values which are selected,
 * a set of values which are not selected, a set of intervals which
 * are selected and a set of intervals which are not selected.
 * Example:
 *                Ranges r = new Ranges("MATNR");
 *                r.addIncludingValue("mbill01");
 *                r.addExcludingValue("knochen");
 *                r.addIncludingInterval("ka", "kz");
 *
 *                Selected values for the attribute MATNR are
 *                "mbill01" and all values between "ka" and "kz"
 *                but "knochen".
 */

//HACK: implements java.io.Serializable for saving ConditionSelectionVariant in file
public class Ranges implements java.io.Serializable {
    private Vector m_includingIntervals = new Vector();
    private Vector m_excludingIntervals = new Vector();
    private Vector m_includingValues = new Vector();
    private Vector m_excludingValues = new Vector();
    private String m_attributeName;

    public Ranges(String attributeName) {
        m_attributeName = attributeName;
    }

    public String getAttributeName() {
        return m_attributeName;
    }

    // TODO (Martin Lueneburg): The next four methods with String arguments should be deleted
    // TODO (Martin Lueneburg): but they are still in use, e.g. by the dictionary testing class
    public void addIncludingValue(String value) {
        m_includingValues.addElement(value);
    }

    public void addExcludingValue(String value) {
        m_excludingValues.addElement(value);
    }

    public void addIncludingInterval(String lowerBound, String upperBound) {
        m_includingIntervals.addElement(new Interval(lowerBound, upperBound));
    }

    public void addExcludingInterval(String lowerBound, String upperBound) {
        m_excludingIntervals.addElement(new Interval(lowerBound, upperBound));
    }

    public void addIncludingValue(sys_variant val) {
        m_includingValues.addElement(val);
    }

    public void addExcludingValue(sys_variant val) {
        m_excludingValues.addElement(val);
    }

    public void addIncludingInterval(sys_variant valLowerBound, sys_variant valUpperBound) {
        m_includingIntervals.addElement(new Interval(valLowerBound, valUpperBound));
    }

    public void addExcludingInterval(sys_variant valLowerBound, sys_variant valUpperBound) {
        m_excludingIntervals.addElement(new Interval(valLowerBound, valUpperBound));
    }

    public void removeIncludingValues() {
        m_includingValues = new Vector();
    }

    public void removeIncludingIntervals() {
        m_includingIntervals = new Vector();
    }

    public void removeExcludingValues() {
        m_excludingValues = new Vector();
    }

    public void removeExcludingIntervals() {
        m_excludingIntervals = new Vector();
    }

    public int getIncludingValuesCount() {
        return m_includingValues.size();
    }

    public int getExcludingValuesCount() {
        return m_excludingValues.size();
    }

    public int getIncludingIntervalsCount() {
        return m_includingIntervals.size();
    }

    public int getExcludingIntervalsCount() {
        return m_excludingIntervals.size();
    }

    public boolean isEmpty() {
        return (getIncludingValuesCount() == 0) && (getExcludingValuesCount() == 0)
            && (getIncludingIntervalsCount() == 0) && (getExcludingIntervalsCount() == 0);
    }

    public Vector getIncludingValues() {
        return m_includingValues;
    }

    public String getIncludingValueAt(int i) {
        return ((sys_variant) (m_includingValues.elementAt(i))).toString();
    }

    public String getExcludingValueAt(int i) {
        return ((sys_variant) (m_excludingValues.elementAt(i))).toString();
    }

    public String getIncludingIntervalLowAt(int i) {
        return ((Interval) m_includingIntervals.elementAt(i)).getLowerBound();
    }

    public String getIncludingIntervalUpAt(int i) {
        return ((Interval) m_includingIntervals.elementAt(i)).getUpperBound();
    }

    public String getExcludingIntervalLowAt(int i) {
        return ((Interval) m_excludingIntervals.elementAt(i)).getLowerBound();
    }

    public String getExcludingIntervalUpAt(int i) {
        return ((Interval) m_excludingIntervals.elementAt(i)).getUpperBound();
    }

    public void setAttributeName(String newAttributeName) {
        m_attributeName = newAttributeName;
    }

    /**@return a partial WHERE clause of an SQL statement
     * example:
     *        including values: iv1, iv2
     *        including intervals: ii1, ii2
     *        excluding values: ev1, ev2
     *        excluding intervals: ei1, ei2
     *        result:
     *        attribute = iv1 or attribute = iv2 or
     *        attribute element of ii1 or attribute element of ii2 and
     *        attribute != ev1 and attribute != ev2 and
     *        attribute not element of ei1 or attribute not element of ei2
     *
     *        Null is allowed for one bound of an interval. In that case this
     *  bound is not considered in the SQL statement.
     *
     */

    // TODO: use query pairs
    public String getSQLCondition(String keywordEscapeSequenceStart, String keywordEscapeSequenceEnd) {
        int includingValuesSize = m_includingValues.size();
        int includingIntervalsSize = m_includingIntervals.size();
        int excludingValuesSize = m_excludingValues.size();
        int excludingIntervalsSize = m_excludingIntervals.size();

        StringBuffer strBuf = new StringBuffer();
        int i;
        String str1;
        String str2;

        //including values
        if (includingValuesSize > 0) {
            if (includingIntervalsSize > 0) {
                strBuf.append("( ");
            }
            strBuf.append("( ");
            for (i = 0; i < includingValuesSize; i++) {
                if (i > 0) {
                    strBuf.append(" OR ");
                }
                strBuf.append(keywordEscapeSequenceStart);
                strBuf.append(m_attributeName);
                strBuf.append(keywordEscapeSequenceEnd);
                strBuf.append(" = '").append(m_includingValues.elementAt(i)).append("'");
            }
            strBuf.append(" )");
        }

        //including intervals
        if (includingIntervalsSize > 0) {
            if (includingValuesSize > 0) {
                strBuf.append(" OR ");
            }
            strBuf.append("( ");
            for (i = 0; i < includingIntervalsSize; i++) {
                if (i > 0) {
                    strBuf.append(" OR ");
                }
                Interval interval = (Interval) m_includingIntervals.elementAt(i);
                if ((str1 = interval.getSQLStringLowerBound()) != null) {
                    strBuf.append(keywordEscapeSequenceStart);
                    strBuf.append(m_attributeName);
                    strBuf.append(keywordEscapeSequenceEnd);
                    strBuf.append(" >= ");

                    //strBuf.append("'").append( str1 ).append("'");
                    strBuf.append(str1);
                }
                str2 = interval.getSQLStringUpperBound();
                if ((str1 != null) && (str2 != null)) {
                    strBuf.append(" AND ");
                }
                if (str2 != null) {
                    strBuf.append(keywordEscapeSequenceStart);
                    strBuf.append(m_attributeName);
                    strBuf.append(keywordEscapeSequenceEnd);
                    strBuf.append(" <= ");

                    //strBuf.append("'").append( str2 ).append("'");
                    strBuf.append(str2);
                }
            }
            strBuf.append(" )");
            if (includingValuesSize > 0) {
                strBuf.append(" )");
            }
        }

        //excluding values
        if (excludingValuesSize > 0) {
            if (strBuf.length() > 0) {
                strBuf.append(" AND ");
            }
            strBuf.append("( ");
            for (i = 0; i < excludingValuesSize; i++) {
                if (i > 0) {
                    strBuf.append(" AND ");
                }
                strBuf.append(keywordEscapeSequenceStart);
                strBuf.append(m_attributeName);
                strBuf.append(keywordEscapeSequenceEnd);
                strBuf.append(" <> ");

                //strBuf.append("'").append(m_excludingValues.elementAt(i)).append("'");
                str1 = ((sys_variant) m_excludingValues.elementAt(i)).getSQLString();
                strBuf.append(str1);
            }
            strBuf.append(" )");
        }

        //excluding intervals
        if (excludingIntervalsSize > 0) {
            if (strBuf.length() > 0) {
                strBuf.append(" AND ");
            }
            strBuf.append("( ");
            for (i = 0; i < excludingIntervalsSize; i++) {
                if (i > 0) {
                    strBuf.append(" AND ");
                }
                strBuf.append(" NOT ( ");
                Interval interval = (Interval) m_excludingIntervals.elementAt(i);
                if ((str1 = interval.getSQLStringLowerBound()) != null) {
                    strBuf.append(keywordEscapeSequenceStart);
                    strBuf.append(m_attributeName);
                    strBuf.append(keywordEscapeSequenceEnd);
                    strBuf.append(" >= ");

                    //strBuf.append("'").append( str1 ).append("'");
                    strBuf.append(str1);
                }
                str2 = interval.getSQLStringUpperBound();
                if ((str1 != null) && (str2 != null)) {
                    strBuf.append(" AND ");
                }
                if (str2 != null) {
                    strBuf.append(keywordEscapeSequenceStart);
                    strBuf.append(m_attributeName);
                    strBuf.append(keywordEscapeSequenceEnd);
                    strBuf.append(" <= ");

                    //strBuf.append("'").append( str2 ).append("'");
                    strBuf.append(str2);
                }
                strBuf.append(" )");
            }
            strBuf.append(" )");
        }

        return strBuf.toString();
    }

    /** For testing.
     */
    public static void main(String[] args) {

        /*
           Ranges r = new Ranges("MATNR");
           r.addIncludingValue("mbill01");
           r.addIncludingValue("mbill02");
           r.addExcludingValue("knochen");
           r.addExcludingValue("gr�te");
           r.addIncludingInterval("ab", "ba");
           r.addIncludingInterval("za", "zk");
           r.addExcludingInterval("ba", "bk");
           r.addExcludingInterval("cc", "dd");
           System.out.println(r.getSQLCondition("[","]"));
           System.out.println("-------------");
         */
    }

    private class Interval implements java.io.Serializable {
        private sys_variant m_valLowerBound;
        private sys_variant m_valUpperBound;
        private String m_lowerBound;
        private String m_upperBound;

        public Interval(String lowerBound, String upperBound) {
            m_lowerBound = lowerBound;
            m_upperBound = upperBound;
        }

        public Interval(sys_variant valLowerBound, sys_variant valUpperBound) {
            m_valLowerBound = valLowerBound;
            m_valUpperBound = valUpperBound;
        }

        public String getLowerBound() {
            if (m_valLowerBound != null) {
                return m_valLowerBound.toString();
            }
            return null;
        }

        public String getUpperBound() {
            if (m_valUpperBound != null) {
                return m_valUpperBound.toString();
            }
            return null;
        }

        public String getSQLStringLowerBound() {
            if (m_valLowerBound != null) {
                return m_valLowerBound.getSQLString();
            }
            return null;
        }

        public String getSQLStringUpperBound() {
            if (m_valUpperBound != null) {
                return m_valUpperBound.getSQLString();
            }
            return null;
        }
    }
}
