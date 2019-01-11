/**************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                               *
 * This program is free software; you can redistribute it and/or modify it    		  *
 * under the terms version 2 or later of the GNU General Public License as published  *
 * by the Free Software Foundation. This program is distributed in the hope           *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied         *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                   *
 * See the GNU General Public License for more details.                               *
 * You should have received a copy of the GNU General Public License along            *
 * with this program; if not, printLine to the Free Software Foundation, Inc.,        *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                             *
 * For the text or an alternative of this public license, you may reach us            *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved.  *
 * Contributor: Yamel Senih ysenih@erpya.com                                          *
 * See: www.erpya.com                                                                 *
 *************************************************************************************/
package org.erpya.lve.bank.imp;

import java.io.FileInputStream;

import org.compiere.impexp.BankStatementLoaderInterface;
import org.compiere.model.MBankStatementLoader;
import org.xml.sax.SAXException;

/**
 * File of Provincial Bank support
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [ 1701 ] Add support to MT940 format
 * @see https://github.com/adempiere/adempiere/issues/1701
 */
public final class Bancaribe_BankStatementLoader extends Bancaribe_BankTransaction implements BankStatementLoaderInterface {

	/**
	 * Method init
	 * @param controller MBankStatementLoader
	 * @return boolean
	 * @see org.compiere.impexp.BankStatementLoaderInterface#init(MBankStatementLoader)
	 */
	public boolean init(MBankStatementLoader controller) {
		boolean result = false;
		FileInputStream m_stream = null;
		try {
			//	Try to open the file specified as a process parameter
			if (controller.getLocalFileName() != null) {
				m_stream = new FileInputStream(controller.getLocalFileName());
			}
			//	Try to open the file specified as part of the loader configuration
			else if (controller.getFileName() != null) {
				m_stream = new FileInputStream(controller.getFileName());
			}
			if (m_stream == null) {
				return result;
			}
			if (!super.init(controller)) {
				return result;
			}
			result = attachInput(m_stream);
		} catch(Exception e) {
			m_errorMessage = "ErrorReadingData";
			m_errorDescription = "";
		}
		//	return
		return result;
	}	//	init
	

	/**
	 * Method characters
	 * @param ch char[]
	 * @param start int
	 * @param length int
	 * @throws SAXException
	 * @see org.xml.sax.ContentHandler#characters(char[], int, int)
	 */
	public void characters (char ch[], int start, int length)
		throws SAXException
	{
		/*
		 * There are no additional things to do when importing from file.
		 * All data is handled by OFXBankStatementHandler
		 */
		super.characters(ch, start, length);
	}	//	characterS
	
}
