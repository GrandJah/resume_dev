package net.devstudy.resume.service;

import net.devstudy.resume.entity.Profile;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public interface FindProfileService {

	Profile findByUid(String uid);
}