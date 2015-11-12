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
package de.geoinfoffm.registry.core.model.iso19135;

import java.util.Calendar;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.envers.Audited;

@Access(AccessType.FIELD)
@DiscriminatorValue("RE_AmendmentInformation")
@Audited @Entity
public class RE_AmendmentInformation extends RE_ProposalManagementInformation {

	@Enumerated(EnumType.STRING)
	private RE_AmendmentType amendmentType;

	public RE_AmendmentInformation() {

	}

	/**
	 * @return the amendmentType
	 */
	public RE_AmendmentType getAmendmentType() {
		return amendmentType;
	}

	/**
	 * @param amendmentType the amendmentType to set
	 */
	public void setAmendmentType(RE_AmendmentType amendmentType) {
		this.amendmentType = amendmentType;
	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/* (non-Javadoc)
	 * @see de.geoinfoffm.registry.core.model.iso19135.RE_ProposalManagementInformation#onDispositionAccepted()
	 */
	@Override
	protected void onDispositionAccepted() {
		RE_RegisterItem item = this.getItem();
		
		item.setDateAmended(Calendar.getInstance().getTime());
		switch (this.amendmentType) {
			case RETIREMENT:
				item.setStatus(RE_ItemStatus.RETIRED);
				break;
			case SUPERSESSION:
				item.setStatus(RE_ItemStatus.SUPERSEDED);
				break;
			case INVALIDATION:
				item.setStatus(RE_ItemStatus.INVALID);
				break;
		}
	}
}//end RE_AmendmentInformation