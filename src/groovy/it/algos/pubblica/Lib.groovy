package it.algos.pubblica

import groovy.sql.Sql
import it.algos.pubblica.PubblicaTagLib.Giorno

import java.sql.Timestamp

/**
 * Created by IntelliJ IDEA.
 * User: Gac
 * Date: 03/11/11
 * Time: 18.30
 */
class Lib {

    private static String aCapo = '\n'
    private static String tab1 = '  '
    private static String tab2 = tab1 + tab1
    private static String tab3 = tab2 + tab1


    static String getTitoloPagina(String testoIn) {
        String testoOut = ''

        //testoOut += '<FONT COLOR=339966 FACE="Geneva, Arial" SIZE=6>'
        //testoOut += testoIn
        //testoOut += '</font>'

        testoOut += '<h1>'
        testoOut += testoIn
        testoOut += '</h1>'

        return testoOut
    }// fine del metodo


    static String getTable(String testoIn) {
        String testoOut = ''

        testoOut += '<table border="1">'
        testoOut += aCapo
        testoOut += testoIn
        testoOut += '</table>'
        testoOut += aCapo

        return testoOut
    }// fine del metodo

    static String getCaption(String testoIn) {
        String testoOut = tab1

        testoOut += '<caption>'
        testoOut += aCapo
        testoOut += tab2
        testoOut += '<div align="center">'
        testoOut += '<b>'
        testoOut += testoIn
        testoOut += '</b>'
        testoOut += '</div>'
        testoOut += aCapo
        testoOut += tab1
        testoOut += '</caption>'
        testoOut += aCapo

        return testoOut
    }// fine del metodo

    static String getHead(String testoIn) {
        String testoOut = tab1

        testoOut += '<thead>'
        testoOut += aCapo
        testoOut += testoIn
        testoOut += tab1
        testoOut += '</thead>'
        testoOut += aCapo

        return testoOut
    }// fine del metodo

    private static String getTitoloBase(String testoIn, String colore, int span) {
        String testoOut = tab3

        testoOut += '<th'
        if (span) {
            testoOut += ' colspan="'
            testoOut += span
            testoOut += '"'
        }// fine del blocco if
        if (colore) {
            testoOut += ' bgcolor=#'
            testoOut += colore
        }// fine del blocco if
        testoOut += '>'

        if (colore) {
            testoOut += '<FONT COLOR=WHITE FACE="Geneva, Arial" SIZE=2>'
        }// fine del blocco if

        testoOut += testoIn
        testoOut += '</th>'
        testoOut += aCapo

        return testoOut
    }// fine del metodo

    static String getTitolo(String testoIn, String colore) {
        return getTitoloBase(testoIn, colore, 0)
    }// fine del metodo

    static String getTitolo(String testoIn) {
        return getTitolo(testoIn, '')
    }// fine del metodo

    static String getTitoloDoppio(String testoIn, String colore) {
        return getTitoloBase(testoIn, colore, 2)
    }// fine del metodo

    static String getTitoloDoppio(String testoIn) {
        return getTitoloDoppio(testoIn, '')
    }// fine del metodo

    static String getBody(String testoIn) {
        String testoOut = tab1

        testoOut += '<tbody>'
        testoOut += aCapo
        testoOut += testoIn
        testoOut += tab1
        testoOut += '</tbody>'
        testoOut += aCapo

        return testoOut
    }// fine del metodo


    static String getRiga(String testoIn) {
        String testoOut = tab2

        testoOut += '<tr>'
        testoOut += aCapo
        testoOut += testoIn
        testoOut += tab2
        testoOut += '</tr>'
        testoOut += aCapo

        return testoOut
    }// fine del metodo


    private static String getCellaBase(String testoIn, String colore, int span) {
        String testoOut = tab3

        testoOut += '<td'
        if (span) {
            testoOut += ' rowspan="'
            testoOut += span
            testoOut += '"'
        }// fine del blocco if-else
        if (colore) {
            testoOut += ' bgcolor=#'
            testoOut += colore
        }// fine del blocco if-else
        testoOut += '>'

        testoOut += testoIn
        testoOut += '</td>'
        testoOut += aCapo

        return testoOut
    }// fine del metodo

    static String getCella(String testoIn, String colore) {
        return getCellaBase(testoIn, colore, 0)
    }// fine del metodo

    static String getCella(String testoIn) {
        return getCella(testoIn, '')
    }// fine del metodo

    static String getCellaDoppia(String testoIn, String colore) {
        return getCellaBase(testoIn, colore, 2)
    }// fine del metodo

    static String getCellaDoppia(String testoIn) {
        return getCellaDoppia(testoIn, '')
    }// fine del metodo

    static String getCellaTripla(String testoIn, String colore) {
        return getCellaBase(testoIn, colore, 3)
    }// fine del metodo

    static String getCellaTripla(String testoIn) {
        return getCellaTripla(testoIn, '')
    }// fine del metodo

    static String getCellaQuattro(String testoIn, String colore) {
        return getCellaBase(testoIn, colore, 4)
    }// fine del metodo

    static String getCellaQuattro(String testoIn) {
        return getCellaQuattro(testoIn, '')
    }// fine del metodo

    static String getBold(String testoIn) {
        String testoOut = ''

        testoOut += '<bold>'
        testoOut += testoIn
        testoOut += '</bold>'

        return testoOut
    }// fine del metodo

    static String getStrong(String testoIn) {
        String testoOut = ''

        testoOut += '<strong>'
        testoOut += testoIn
        testoOut += '</strong>'

        return testoOut
    }// fine del metodo

    public static String getWeek(int numWeek) {
        String testo = ''

        numWeek--
        for (Giorno giorno : Giorno.values()) {
            if (giorno.ordinal() == numWeek) {
                testo = giorno.toString()
            }// fine del blocco if
        } // fine del ciclo for-each

        return testo
    }// fine del metodo

    public static int getOre(Date giorno) {
        int ore
        Calendar cal = Calendar.getInstance()

        cal.setTime(giorno)
        ore = cal.get(GregorianCalendar.HOUR_OF_DAY)

        return ore
    }// fine del metodo

    public static int getMinuti(Date giorno) {
        int minuti
        Calendar cal = Calendar.getInstance()

        cal.setTime(giorno)
        minuti = cal.get(GregorianCalendar.MINUTE)

        return minuti
    }// fine del metodo

    public static int difOre(Date giornoFine, Date giornoInizio) {
        int ore
        long fine
        long inizio
        long differenza

        fine = giornoFine.time
        inizio = giornoInizio.time
        differenza = fine - inizio
        differenza = differenza / 1000
        differenza = differenza / 60
        differenza = differenza / 60
        ore = differenza.intValue()

        return ore
    }// fine del metodo

    public static String getDataSql(Date giorno) {
        String testo = ''
        String sep = '' //formato ISO
        int anno
        int mese
        int giornoMese
        String meseTxt
        String giornoTxt

        Calendar cal = Calendar.getInstance()
        cal.setTime(giorno)
        anno = cal.get(GregorianCalendar.YEAR)
        mese = cal.get(GregorianCalendar.MONTH) + 1
        giornoMese = cal.get(GregorianCalendar.DAY_OF_MONTH)

        meseTxt = mese + ''
        if (meseTxt.length() < 2) {
            meseTxt = '0' + meseTxt
        }// fine del blocco if

        giornoTxt = giornoMese + ''
        if (giornoTxt.length() < 2) {
            giornoTxt = '0' + giornoTxt
        }// fine del blocco if

        testo += anno
        testo += sep
        testo += meseTxt
        testo += sep
        testo += giornoTxt

        return testo
    }// fine del metodo

    /**
     * Crea una data.
     * <p/>
     *
     * @param giorno il giorno del mese (1 per il primo)
     * @param mese il mese dell'anno (1 per gennaio)
     * @param anno l'anno
     *
     * @return la data creata
     */
    public static Timestamp creaData() {
        /* variabili e costanti locali di lavoro */
        Date giorno = new Date()
        Calendar cal

        try { // prova ad eseguire il codice
            cal = Calendar.getInstance()
            cal.setTime(giorno)

            cal.set(Calendar.HOUR_OF_DAY, 0)
            cal.set(Calendar.MINUTE, 0)
            cal.set(Calendar.SECOND, 0)
            cal.set(Calendar.MILLISECOND, 0)

            giorno = new java.util.Date(cal.getTime().getTime());

        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        /* valore di ritorno */
        return giorno.toTimestamp()
    }// fine del metodo

    /**
     * Crea la data del primo gennaio corrente anno.
     * <p/>
     *
     * @param giorno il giorno del mese (1 per il primo)
     * @param mese il mese dell'anno (1 per gennaio)
     * @param anno l'anno
     *
     * @return la data creata
     */
    public static Timestamp creaData1Gennaio() {
        /* variabili e costanti locali di lavoro */
        Date giorno = new Date()
        Calendar cal

        try { // prova ad eseguire il codice
            cal = Calendar.getInstance()
            cal.setTime(giorno)
            cal.set(Calendar.MONTH, 0)
            cal.set(Calendar.DAY_OF_MONTH, 1)
            cal.set(Calendar.HOUR_OF_DAY, 0)
            cal.set(Calendar.MINUTE, 0)
            cal.set(Calendar.SECOND, 0)
            cal.set(Calendar.MILLISECOND, 0)

            giorno = new java.util.Date(cal.getTime().getTime());

        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        /* valore di ritorno */
        return giorno.toTimestamp()
    }// fine del metodo

    /**
     * Restituisce la data corrente.
     * <p/>
     *
     * @return la data corrente in formato testo comprensibile per il database sql
     */
    public static String creaDataSql() {
        /* variabili e costanti locali di lavoro */
        String giornoTxt = ''
        Date giorno = Lib.creaData()

        if (giorno) {
            giornoTxt = Lib.getDataSql(giorno)
        }// fine del blocco if

        /* valore di ritorno */
        return giornoTxt
    }// fine del metodo

    /**
     * Restituisce la data del primo gennaio corrente anno.
     * <p/>
     *
     * @return la data creata in formato testo comprensibile per il database sql
     */
    public static String creaData1GennaioSql() {
        /* variabili e costanti locali di lavoro */
        String giornoTxt = ''
        Date giorno = Lib.creaData1Gennaio()

        if (giorno) {
            giornoTxt = Lib.getDataSql(giorno)
        }// fine del blocco if

        /* valore di ritorno */
        return giornoTxt
    }// fine del metodo

    /**
     * Restituisce stringa formatta della data.
     *
     * @return la data creata in formato testo italiano
     */
    public static String getData(Date giorno) {
        /* variabili e costanti locali di lavoro */
        String giornoTxt = ''
        Calendar cal = Calendar.getInstance()
        int numSettimana
        String textSettimana
        def settimana = ['dom', 'lun', 'mar', 'mer', 'gio', 'ven', 'sab']

        if (giorno) {
            giornoTxt = giorno.toLocaleString()
            giornoTxt = giornoTxt.substring(0, giornoTxt.indexOf(' ')).trim()
            giornoTxt = giornoTxt.replace('2012', '12')

            cal.setTime(giorno)
            numSettimana = cal.get(GregorianCalendar.DAY_OF_WEEK)
            textSettimana = settimana[--numSettimana]

            giornoTxt = textSettimana + ', ' + giornoTxt
        }// fine del blocco if

        /* valore di ritorno */
        return giornoTxt
    }// fine del metodo

    /**
     * Restituisce la settimana dell'anno.
     */
    public static int getSettimana(Date giorno) {
        /* variabili e costanti locali di lavoro */
        int settimana = 0
        Calendar cal

        try { // prova ad eseguire il codice
            cal = Calendar.getInstance()
            cal.setTime(giorno)
            settimana = cal.get(GregorianCalendar.WEEK_OF_YEAR)
        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        /* valore di ritorno */
        return settimana
    }// fine del metodo

    /**
     * Testo descrittivo di una settimana (periodo)
     */
    public static String getTestoSettimana(Date giorno) {
        /* variabili e costanti locali di lavoro */
        String testo = 'dal '
        Calendar cal = Calendar.getInstance()

        if (giorno) {
            giorno = Lib.getLunedi(giorno)
            testo += Lib.getGiornoMese(giorno)
            testo += ' al '
            cal.setTime(giorno + 6)
            testo += Lib.getGiornoMese(giorno + 6)
        }// fine del blocco if

        // valore di ritorno
        return testo
    }// fine del metodo

    /**
     * API per interrogare direttamente il database
     *
     * @return API per interrogare direttamente il database
     */
    private static Sql sqlApiBase(String database) {
        /* variabili e costanti locali di lavoro */
        Sql sql
        def db
        String url = "jdbc:mysql://localhost/${database}"
        String username = 'root'
        String password = ''
        String driverClassName = 'com.mysql.jdbc.Driver'

        db = [url: url, user: username, password: password, driver: driverClassName]
        sql = Sql.newInstance(db.url, db.user, db.password, db.driver)

        /* valore di ritorno */
        return sql
    }// fine del metodo

    /**
     * API per interrogare direttamente il database delle informazioni
     *
     * @return API per interrogare direttamente il database delle informazioni
     */
    public static Sql sqlApiSchema() {
        return Lib.sqlApiBase('information_schema')
    }// fine del metodo

    /**
     * Colonne (campi) di un database.
     * <p/>
     *
     * @param database
     * @param tavola
     * @return lista delle colonne
     */
    public static ArrayList<String> sqlColonne(String database, String tavola) {
        /* variabili e costanti locali di lavoro */
        ArrayList<String> lista = null
        Sql sql = Lib.sqlApiSchema()
        def db
        String query
        String databaseLower = database?.toLowerCase()
        String tavolaLower = tavola?.toLowerCase()
        def mappa
        String nome

        query = "SELECT * FROM `COLUMNS` where table_schema='${databaseLower}' and table_name='${tavolaLower}' order by column_name"

        if (database && tavola && sql) {
            mappa = sql.rows(query)
            if (mappa && mappa.size() > 0) {
                lista = new ArrayList<String>()
                mappa?.each {
                    nome = it.column_name
                    lista.add(nome)
                }// fine del blocco each
            }// fine del blocco if
        }// fine del blocco if

        if (sql) {
            sql.close()
        }// fine del blocco if

        /* valore di ritorno */
        return lista
    }// fine del metodo

    /**
     * Elimina una colonna se esiste.
     * <p/>
     *
     * @param database
     * @param tavola
     * @param colonna
     */
    public static void eliminaColonnaSeEsiste(String database, String tavola, String colonna) {
        if (Lib.isEsisteColonna(database, tavola, colonna)) {
            Lib.eliminaColonna(database, tavola, colonna)
        }// fine del blocco if
    }// fine del metodo

    /**
     * Controlla l'esistenza di una colonna in un database.
     * <p/>
     *
     * @param database
     * @param tavola
     * @param colonna
     * @return esistenza della colonna
     */
    public static boolean isEsisteColonna(String database, String tavola, String colonna) {
        /* variabili e costanti locali di lavoro */
        boolean esiste = false
        ArrayList<String> lista
        def db
        String colonnaSql = Lib.underscoreSeparatedCase(colonna)

        if (database && tavola && colonna) {
            lista = Lib.sqlColonne(database, tavola)
            if (lista) {
                lista?.each {
                    if (it.equals(colonnaSql)) {
                        esiste = true
                    }// fine del blocco if
                }// fine del blocco each
            }// fine del blocco if
        }// fine del blocco if

        /* valore di ritorno */
        return esiste
    }// fine del metodo

    /**
     * Elimina una colonna
     * <p/>
     *
     * @param database
     * @param tavola
     * @param colonna
     * @return conferma eliminazione
     */
    public static boolean eliminaColonna(String database, String tavola, String colonna) {
        /* variabili e costanti locali di lavoro */
        boolean eliminata = false
        Sql sql = Lib.sqlApiBase(database?.toLowerCase())
        String update
        String tavolaLower = tavola?.toLowerCase()
        String colonnaSql = Lib.underscoreSeparatedCase(colonna)
        update = "ALTER TABLE ${tavolaLower} DROP COLUMN ${colonnaSql}"

        if (database && tavola && colonna) {
            try { // prova ad eseguire il codice
                sql.executeUpdate(update)
                eliminata = true
            } catch (Exception unErrore) { // intercetta l'errore
            }// fine del blocco try-catch
        }// fine del blocco if

        if (sql) {
            sql.close()
        }// fine del blocco if

        /* valore di ritorno */
        return eliminata
    }// fine del metodo


    public static String underscoreSeparatedCase(String s) {
        return s.replaceAll(/\B[A-Z]/) { '_' + it }.toLowerCase()
    }

    public static String propertyNameCase(String s) {
        // Handle null and empty strings.
        if (s == null || s.trim().length() == 0) return s;
        String str;
        if (s.indexOf('_') != -1) {

            StringBuilder buf = new StringBuilder();
            String[] tokens = s.split("_");
            for (String token : tokens) {
                if (token == null || token.length() == 0) continue;
                buf.append(token.substring(0, 1).toUpperCase()).append(token.substring(1));
            }
            str = buf.toString();
        } else {
            str = s;
        }
        if (str.length() > 0) {
            str = str.substring(0, 1).toLowerCase() + str.substring(1);
        }
        return str;
    }// fine del metodo

    /**
     * Formattazione di un numero.
     * <p/>
     * Il numero può arrivare come stringa, intero o double
     * Se la stringa contiene punti e virgole, viene pulita
     * Se la stringa non è convertibile in numero, viene restituita uguale
     * Inserisce il punto separatore ogni 3 cifre
     * Se arriva un oggetto non previsto, restituisce l'oggetto

     * @param obj da formattare (stringa, intero o double)
     * @return numero formattato
     */
    public static formatNum(def obj) {
        // variabili e costanti locali di lavoro
        def formattato
        String numero
        String sep = '.'
        int len
        String num3
        String num6
        String num9
        String num12

        // controllo di congruità
        if (obj in String || obj in Integer || obj in Double) {
            formattato = ''

            if (obj in Integer) {
                return formatNum(String.valueOf(obj))
            }// fine del blocco if

            if (obj in String) {
                numero = (String) obj
                numero = levaTesto(numero, ',')
                numero = levaTesto(numero, '.')
                formattato = numero

                try { // prova ad eseguire il codice
                    Integer.decode(numero)
                    len = numero.length()
                    if (len > 3) {
                        num3 = numero.substring(0, len - 3)
                        num3 += sep
                        num3 += numero.substring(len - 3)
                        formattato = num3
                        if (len > 6) {
                            num6 = num3.substring(0, len - 6)
                            num6 += sep
                            num6 += num3.substring(len - 6)
                            formattato = num6
                            if (len > 9) {
                                num9 = num6.substring(0, len - 9)
                                num9 += sep
                                num9 += num6.substring(len - 9)
                                formattato = num9
                                if (len > 12) {
                                    num12 = num9.substring(0, len - 12)
                                    num12 += sep
                                    num12 += num9.substring(len - 12)
                                    formattato = num12
                                }// fine del blocco if
                            }// fine del blocco if
                        }// fine del blocco if
                    }// fine del blocco if
                } catch (Exception unErrore) { // intercetta l'errore
                }// fine del blocco try-catch
            }// fine del blocco if
        }// fine del blocco if

        // valore di ritorno
        return formattato
    } // fine della closure

    /**
     * Elimina tutti i caratteri contenuti nella stringa.
     * <p/>
     * Esegue solo se il testo è valido
     * Se arriva un oggetto non stringa, restituisce l'oggetto
     *
     * @param testoIn in ingresso
     * @param subStringa da eliminare
     * @return testoOut stringa convertita
     */
    public static levaTesto(testoIn, subStringa) {
        // variabili e costanti locali di lavoro
        def testoOut = testoIn

        if (testoIn in String) {
            testoOut = testoIn.trim()
            if (testoOut.contains(subStringa)) {
                testoOut = sostituisce(testoOut, subStringa, '')
            }// fine del blocco if
        }// fine del blocco if

        // valore di ritorno
        return testoOut
    } // fine della closure

    /**
     * Sostituisce tutte le occorrenze.
     * <p/>
     * Esegue solo se il testo è valido
     * Se arriva un oggetto non stringa, restituisce l'oggetto
     *
     * @param testoIn in ingresso
     * @param oldStringa da eliminare
     * @param newStringa da sostituire
     * @return testoOut convertito
     */
    public static sostituisce(testoIn, oldStringa, newStringa) {
        // variabili e costanti locali di lavoro
        def testoOut = testoIn
        int pos = 0
        String prima = ''
        String dopo

        if (testoIn in String && oldStringa in String && newStringa in String) {
            testoOut = testoIn.trim()
            if (testoIn.contains(oldStringa)) {

                while (pos != -1) {
                    pos = testoIn.indexOf(oldStringa)
                    if (pos != -1) {
                        prima += testoIn.substring(0, pos)
                        prima += newStringa
                        pos += oldStringa.length()
                        testoIn = testoIn.substring(pos)
                    }// fine del blocco if
                }// fine di while

                testoOut = prima + testoIn
            }// fine del blocco if
        }// fine del blocco if

        // valore di ritorno
        return testoOut
    } // fine della closure

    /**
     * Restituisce il primo giorno della settimana (lunedì)
     * relativo alla data ricevuta come parametro
     *
     * @param giorno
     * @return primo giorno della settimana
     */
    public static Date getLunedi(Date giornoIn) {
        /* variabili e costanti locali di lavoro */
        Date giornoOut = null
        Calendar cal
        int numWeek

        if (giornoIn) {
            giornoOut = giornoIn
            cal = Calendar.getInstance()
            cal.setTime(giornoOut)

            while (cal.get(Calendar.DAY_OF_WEEK) != 2) {
                giornoOut = giornoOut - 1
                cal.setTime(giornoOut)
            }// fine del blocco while
        }// fine del blocco if

        /* valore di ritorno */
        return giornoOut
    } // fine del metodo

    /**
     * Restituisce la data come 12-set
     *
     * @param giorno
     * @return primo giorno della settimana
     */
    public static String getGiornoMese(Date giorno) {
        /* variabili e costanti locali di lavoro */
        String data = ''
        String sep = '-'
        Calendar cal
        int numMese

        def mesi = ['gen', 'feb', 'mar', 'apr', 'mag', 'giu', 'lug', 'ago', 'set', 'ott', 'nov', 'dic']

        if (giorno) {
            cal = Calendar.getInstance()
            cal.setTime(giorno)
            data += cal.get(GregorianCalendar.DAY_OF_MONTH)
            data += sep
            numMese = cal.get(GregorianCalendar.MONTH)
            data += mesi[numMese]
        }// fine del blocco if

        /* valore di ritorno */
        return data
    } // fine del metodo

    /**
     * Tag colore
     */
    public static String setRosso(String testoIn) {
        /* variabili e costanti locali di lavoro */
        String testoOut = ''

        if (testoIn) {
            testoOut = '<span style="color:red">'
            testoOut += testoIn
            testoOut += '</span>'
        }// fine del blocco if

        /* valore di ritorno */
        return testoOut
    } // fine del metodo

    /**
     * Tag colore
     */
    public static String setVerde(String testoIn) {
        /* variabili e costanti locali di lavoro */
        String testoOut = ''

        if (testoIn) {
            testoOut = '<span style="color:green">'
            testoOut += testoIn
            testoOut += '</span>'
        }// fine del blocco if

        /* valore di ritorno */
        return testoOut
    } // fine del metodo

} // fine della classe
