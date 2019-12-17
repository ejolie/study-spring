import React, { useEffect } from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';

import { getBacklog } from '../actions/projectTaskAction';
import ProjectTaskItem from './ProjectTask/ProjectTaskItem';

function ProjectBoard({ getBacklog, project_task }) {
  console.log('project_task');
  console.log(project_task);

  useEffect(() => {
    getBacklog();
  }, []);

  const { project_tasks } = project_task;

  let BoardContent;
  let todoItems = [];
  let inProgressItems = [];
  let doneItems = [];

  const BoardAlgorithm = project_tasks => {
    if (project_tasks.length < 1) {
      return (
        <div className='alert alert-info text-center'>
          No Project Tasks on this board
        </div>
      );
    }

    const tasks = project_tasks.map(project_task => (
      <ProjectTaskItem key={project_task.id} project_task={project_task} />
    ));

    for (let task of tasks) {
      const taskStatus = task.props.project_task.status;
      if (taskStatus === 'TO_DO') {
        todoItems.push(task);
      } else if (taskStatus === 'IN_PROGRESS') {
        inProgressItems.push(task);
      } else {
        doneItems.push(task);
      }
    }
    return (
      <>
        <div className='container'>
          <div className='row'>
            <div className='col-md-4'>
              <div className='card text-center mb-2'>
                <div className='card-header bg-secondary text-white'>
                  <h3>TO DO</h3>
                </div>
              </div>
              {todoItems}
            </div>
            <div className='col-md-4'>
              <div className='card text-center mb-2'>
                <div className='card-header bg-primary text-white'>
                  <h3>In Progress</h3>
                </div>
              </div>
              {inProgressItems}
            </div>
            <div className='col-md-4'>
              <div className='card text-center mb-2'>
                <div className='card-header bg-success text-white'>
                  <h3>Done</h3>
                </div>
              </div>
              {doneItems}
            </div>
          </div>
        </div>
      </>
    );
  };

  BoardContent = BoardAlgorithm(project_tasks);

  return (
    <div className='container'>
      <Link to='/addProjectTask' className='btn btn-primary mb-3'>
        <i className='fas fa-plus-circle'> Create Project Task</i>
      </Link>
      <br />
      <hr />
      {BoardContent}
    </div>
  );
}

ProjectBoard.propTypes = {
  getBacklog: PropTypes.func.isRequired,
  project_tasks: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  project_tasks: state.project_task
});

export default connect(mapStateToProps, { getBacklog })(ProjectBoard);
