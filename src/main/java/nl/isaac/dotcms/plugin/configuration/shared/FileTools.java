package nl.isaac.dotcms.plugin.configuration.shared;
/**
* dotCMS Configuration plugin by ISAAC - The Full Service Internet Agency is licensed 
* under a Creative Commons Attribution 3.0 Unported License
* - http://creativecommons.org/licenses/by/3.0/
* - http://www.geekyplugins.com/
* 
* @copyright Copyright (c) 2011 ISAAC Software Solutions B.V. (http://www.isaac.nl)
*/

import com.dotmarketing.beans.Host;
import com.dotmarketing.beans.Identifier;
import com.dotmarketing.business.APILocator;
import com.dotmarketing.exception.DotDataException;
import com.dotmarketing.exception.DotSecurityException;
import com.dotmarketing.portlets.contentlet.model.Contentlet;
import com.dotmarketing.portlets.fileassets.business.FileAsset;
import com.liferay.portal.model.User;

public class FileTools {

	public static FileAsset getFileAssetByURI(String URI, Host host, boolean isLive) {
		try {
			User systemUser = APILocator.getUserAPI().getSystemUser();
			long defaultLanguage = APILocator.getLanguageAPI().getDefaultLanguage().getId();
			
			Identifier identifier = APILocator.getIdentifierAPI().find(host, URI);
			Contentlet fileContentlet = APILocator.getContentletAPI().findContentletByIdentifier(identifier.getId(), isLive, defaultLanguage, systemUser, false );
			
			return APILocator.getFileAssetAPI().fromContentlet(fileContentlet);
		} catch (DotSecurityException e) {
			throw new RuntimeException(e);
		} catch (DotDataException e) {
			throw new RuntimeException(e);
		}
	}
}