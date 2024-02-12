package com.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.DepartmentDao;
import com.app.dao.EmployeeDao;
import com.app.dto.ApiResponse;
import com.app.dto.EmployeeDTO;
import com.app.dto.SigninRequest;
import com.app.entities.Department;
import com.app.entities.Employee;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
//dep :
	@Autowired
	private EmployeeDao empDao;
	// dep
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private DepartmentDao deptDao;
	
	@Override
	public List<Employee> getAllEmps() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee addEmpDetails(Employee emp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getEmpDetails(Long empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeDTO authenticateEmployee(SigninRequest request) {
		// invoke dao's method
		return mapper.map(empDao.findByEmailAndPassword
				(request.getEmail(), request.getPassword()) //Optionale<Emp>
				.orElseThrow(
						() -> 
						new ResourceNotFoundException("Invalid email or password !!!")),
				EmployeeDTO.class);
	}

	@Override
	public List<EmployeeDTO> getEmpsByCardDate(LocalDate cardDate) {
		// TODO Auto-generated method stub
		return empDao.findByCardCreatedOnBefore(cardDate) // List<Emp>
				.stream() // Stream<Emp>
				.map(emp -> mapper.map(emp, EmployeeDTO.class)) // Stream<dto>
				.collect(Collectors.toList());// List<dto>

	}

	@Override
	public List<EmployeeDTO> getAllEmployees(int pageNumber, int pageSize) {
		
		// Creates a PageRequest(imple class of Pageable : i/f for pagination) based
		// upon page no n size
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		// fetches the Page of Emps --> getContent() --> List<Emp>
		List<Employee> empList = empDao.findAll(pageable).getContent();
		return empList.stream()
				.map(emp -> 
				mapper.map(emp, EmployeeDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public ApiResponse addEpmWithDeptId(Long deptId, EmployeeDTO eDto) {
		Department dept = deptDao.findById(deptId).orElseThrow();
		Employee emp= mapper.map(eDto,Employee.class);
		dept.addEmployee(emp);
		empDao.save(emp);
		
		return new ApiResponse("successfully added new employee"+ dept.getName());
	}
	

}
