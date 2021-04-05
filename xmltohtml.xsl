<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xs" version="2.0">


    <xsl:template match="/bibliotheque">
        <html lang="fr">
            <head>
                <title>Bibliothèque</title>
                <meta charset="UTF-8">
                    <link rel="stylesheet" type="text/css" href="css/util.css"/>
                    <link rel="stylesheet" type="text/css" href="css/main.css"/>
                </meta>
            </head>
            <body>

                <div class="limiter">
                    <div class="container-table100">
                        <div class="wrap-table100">
                            <div class="table100">
                                <table>
                                    <thead>
                                        <tr class="table100-head">
                                            <th class="column1">Titre</th>
                                            <th class="column2">Auteur</th>
                                            <th class="column3">Date de publication</th>
                                            <th class="column4">Résumé</th>
                                            <th class="column5">Nombre tomes</th>
                                            <th class="column6">Langue</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <xsl:apply-templates/>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="livre">
        <tr>
            <td class="column1"><xsl:value-of select="titre"/></td>
            <td class="column2"><xsl:value-of select="auteur"/></td>
            <td class="column3"><xsl:value-of select="date_publication"/></td>
            <td class="column4" style="text-align:justify;"><xsl:value-of select="resume"/></td>
            <td class="column5">
                <xsl:if test="nb_tomes"><xsl:value-of select="nb_tomes"/></xsl:if>
                <xsl:if test="not(nb_tomes)"> 1 </xsl:if>
            </td>
            <td class="column6">
                <xsl:if test="@lang"><xsl:value-of select="@lang"/></xsl:if>
                <xsl:if test="not(@lang)"> fr </xsl:if>
            </td>
        </tr>
    </xsl:template>


</xsl:stylesheet>
