/*
 * AdministratorCompanyDeleteService.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banner.Banner;
import acme.framework.components.accounts.Administrator;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AdministratorBannerDeleteService extends AbstractService<Administrator, Banner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorBannerRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Banner object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findBannerById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Banner object) {
		assert object != null;

		super.bind(object, "moment", "startPeriod", "endPeriod", "linkPicture", "slogan", "linkWebDocument");
	}

	@Override
	public void validate(final Banner object) {
		assert object != null;
	}

	@Override
	public void perform(final Banner object) {
		assert object != null;

		this.repository.delete(object);
	}

	@Override
	public void unbind(final Banner object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "moment", "startPeriod", "endPeriod", "linkPicture", "slogan", "linkWebDocument");

		super.getResponse().setData(tuple);
	}

}
