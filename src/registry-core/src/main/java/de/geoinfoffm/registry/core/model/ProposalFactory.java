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
package de.geoinfoffm.registry.core.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.geoinfoffm.registry.core.model.iso19135.ProposalManagementInformationRepository;
import de.geoinfoffm.registry.core.model.iso19135.RE_AdditionInformation;
import de.geoinfoffm.registry.core.model.iso19135.RE_AmendmentInformation;
import de.geoinfoffm.registry.core.model.iso19135.RE_ClarificationInformation;
import de.geoinfoffm.registry.core.model.iso19135.RE_ProposalManagementInformation;

/**
 * @author Florian Esser
 *
 */
@Component
public class ProposalFactory implements Proposal.Factory
{
	@Autowired
	private ProposalManagementInformationRepository pmiRepository; 
	
	/* (non-Javadoc)
	 * @see de.geoinfoffm.registry.core.model.Proposal.Factory#createProposal(de.geoinfoffm.registry.core.model.iso19135.RE_ProposalManagementInformation)
	 */
	@Override
	public Proposal createProposal(RE_ProposalManagementInformation proposalManagementInformation) {
		if (proposalManagementInformation instanceof RE_AdditionInformation) {
			return new Addition((RE_AdditionInformation)proposalManagementInformation);
		}
		else if (proposalManagementInformation instanceof RE_ClarificationInformation) {
			return new Clarification((RE_ClarificationInformation)proposalManagementInformation);
		}
		else if (proposalManagementInformation instanceof RE_AmendmentInformation) {
			RE_AmendmentInformation ai = (RE_AmendmentInformation)proposalManagementInformation;
			switch (ai.getAmendmentType()) {
				case RETIREMENT:
					return new Retirement(ai);
				case SUPERSESSION:
					return new SupersessionPart(ai);
			}
		}

		return null;
	}
}
