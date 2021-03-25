<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" version="1.0">
    <xsl:output encoding="UTF-8" indent="yes" method="xml" standalone="no" omit-xml-declaration="no"/>
    <xsl:template match="bibliotheque">
        <fo:root language="FR">
            
            <!-- Page de titre -->
            <fo:layout-master-set>
                <fo:simple-page-master master-name="titre"
                    page-height="29.7cm"
                    page-width="21cm"
                    margin-top="1cm"
                    margin-bottom="1cm"
                    margin-left="2.5cm"
                    margin-right="2.5cm">
                    <fo:region-body background-color="#E8F6F3"/>
                    <fo:region-before extent="1.5cm"/>
                    <fo:region-after extent="1.5cm"/>
                </fo:simple-page-master>
                
                
                <fo:simple-page-master master-name="livres" page-height="297mm" page-width="210mm" margin-top="5mm" margin-bottom="5mm" margin-left="5mm" margin-right="5mm">
                    <fo:region-body margin-top="25mm" margin-bottom="20mm"/>
                    <fo:region-before region-name="xsl-region-before" extent="25mm" display-align="before" precedence="true"/>
                </fo:simple-page-master>
                
            </fo:layout-master-set>
            
            <fo:page-sequence master-reference="titre">
                <fo:flow flow-name="xsl-region-body">
                    
                        
					<fo:block font-size="30pt"
						space-after.optimum="200pt"
						text-align="end">
						
						Avril 2021
						
					</fo:block>
					
					<fo:block font-size="48pt"
						font-family="sans-serif"
						line-height="48pt"
						space-after.optimum="300pt"
						color="#76D7C4"
						text-align="center"
						padding-top="3pt">
						
						Projet JAVA
						
					</fo:block>
                    
                    <fo:block-container height="3cm" width="10cm" top="22cm" left="2.5cm" position="absolute">
                        <fo:block font-size="20pt" line-height="20pt" text-align="start" >
                            Solveig PODER et Camille REY
                        </fo:block>
                    </fo:block-container>
                    
                    <fo:block font-size="26pt"
                        font-family="sans-serif"
                        line-height="26pt"
                        space-after.optimum="0pt"
                        text-align="center">
                        
                        INALCO, M2 IM
                        
                    </fo:block>
                    
                </fo:flow>
            </fo:page-sequence>
            
            

			<fo:page-sequence master-reference="livres">
				<fo:flow flow-name="xsl-region-body" border-collapse="collapse" reference-orientation="0">
					<xsl:for-each select="//livre">
						<fo:block color="#76D7C4"><xsl:value-of select="titre"/></fo:block>
						<fo:table table-layout="fixed" width="100%" font-size="10pt" border-color="black" border-width="0.35mm" border-style="solid" text-align="center" display-align="center" space-after="5mm" font-family="serif">
							<fo:table-column column-width="proportional-column-width(20)" border-width="1px" border-style="solid" background-color="#E8F6F3"/>
							<fo:table-column column-width="proportional-column-width(100)" border-width="1px" border-style="solid"/>
							<fo:table-body font-size="95%">
								<fo:table-row height="10mm" border-width="1px" border-style="solid">
									<fo:table-cell>
										<fo:block font-weight="bold">Titre</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block>
											<xsl:value-of select="titre"/>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row height="10mm" border-width="1px" border-style="solid">
									<fo:table-cell>
										<fo:block font-weight="bold">Auteur</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block>
											<xsl:value-of select="auteur"/>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row height="10mm" border-width="1px" border-style="solid">
									<fo:table-cell>
										<fo:block font-weight="bold">Langue</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block>
											<xsl:if test="@lang"><xsl:value-of select="@lang"/></xsl:if>
											<xsl:if test="not(@lang)"> fr </xsl:if>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row height="10mm" border-width="1px" border-style="solid">
									<fo:table-cell>
										<fo:block font-weight="bold">Tomes</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block>
											<xsl:if test="nb_tomes"><xsl:value-of select="nb_tomes"/></xsl:if>
											<xsl:if test="not(nb_tomes)"> 1 </xsl:if>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row height="10mm" border-width="1px" border-style="solid">
									<fo:table-cell>
										<fo:block font-weight="bold">Publication</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block>
											<xsl:value-of select="date_publication"/>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row border-width="1px" border-style="solid">
									<fo:table-cell>
										<fo:block font-weight="bold">Résumé</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block>
											<xsl:value-of select="resume"/>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</xsl:for-each>
				</fo:flow>
			</fo:page-sequence>
        </fo:root>
        
    </xsl:template>
</xsl:stylesheet>