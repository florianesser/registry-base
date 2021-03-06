/**
 * Copyright (c) 2014, German Federal Agency for Cartography and Geodesy
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *     * Redistributions of source code must retain the above copyright
 *     	 notice, this list of conditions and the following disclaimer.

 *     * Redistributions in binary form must reproduce the above
 *     	 copyright notice, this list of conditions and the following
 *       disclaimer in the documentation and/or other materials
 *       provided with the distribution.

 *     * The names "German Federal Agency for Cartography and Geodesy",
 *       "Bundesamt für Kartographie und Geodäsie", "BKG", "GDI-DE",
 *       "GDI-DE Registry" and the names of other contributors must not
 *       be used to endorse or promote products derived from this
 *       software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE GERMAN
 * FEDERAL AGENCY FOR CARTOGRAPHY AND GEODESY BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 * IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package de.geoinfoffm.registry.core.model.iso19103;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.namespace.QName;

import org.hibernate.envers.Audited;

import de.geoinfoffm.registry.core.ValueObject;

/**
 * Class for codelist values.
 * 
 * @author Florian Esser
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Access(AccessType.FIELD)
@Audited @MappedSuperclass
public abstract class CodeListValue extends ValueObject
{
	private static final long serialVersionUID = -2457064963628233278L;

	@XmlAttribute(name = "codeList", namespace = "http://www.isotc211.org/2005/gmd", required = true)
	@Column(columnDefinition = "text")
	private String codeList;
	@XmlAttribute(name = "codeListValue", namespace = "http://www.isotc211.org/2005/gmd", required = true)
	@Column(columnDefinition = "text")
	private String codeListValue;
	@XmlAttribute(name = "codeSpace", namespace = "http://www.isotc211.org/2005/gmd")
	@Column(columnDefinition = "text")
	private String codeSpace;
	
	@XmlValue
	@Column(columnDefinition = "text")
	private String value;
	
	@XmlTransient
	private String qname;
	
	protected CodeListValue() {
		
	}
	
	public CodeListValue(String codeList, String codeListValue, String value, QName type) {
		this(codeList, codeListValue, null, value, type);
	}
	
	public CodeListValue(String codeList, String codeListValue, String codeSpace, String value, QName type) {
		this.codeList = codeList;
		this.codeListValue = codeListValue;
		this.codeSpace = codeSpace;
		this.qname = type.toString();
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
	
	public String getCodeList() {
		return codeList;
	}
	
	public String getCodeListValue() {
		return codeListValue;
	}

	public String getCodeSpace() {
		return codeSpace;
	}
}
