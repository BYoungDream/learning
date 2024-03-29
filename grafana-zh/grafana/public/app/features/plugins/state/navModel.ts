import _ from 'lodash';
import { DataSource, PluginMeta, NavModel } from 'app/types';

export function buildNavModel(ds: DataSource, plugin: PluginMeta, currentPage: string): NavModel {
  let title = 'New';
  const subTitle = `Type: ${plugin.name}`;

  if (ds.id) {
    title = ds.name;
  }

  const main = {
    img: plugin.info.logos.large,
    id: 'ds-edit-' + plugin.id,
    subTitle: subTitle,
    url: '',
    text: title,
    breadcrumbs: [{ title: '数据源', url: 'datasources' }],
    children: [
      {
        active: currentPage === 'datasource-settings',
        icon: 'fa fa-fw fa-sliders',
        id: 'datasource-settings',
        text: '设置',
        url: `datasources/edit/${ds.id}`,
      },
    ],
  };

  const hasDashboards = _.find(plugin.includes, { type: 'dashboard' }) !== undefined;
  if (hasDashboards && ds.id) {
    main.children.push({
      active: currentPage === 'datasource-dashboards',
      icon: 'fa fa-fw fa-th-large',
      id: 'datasource-dashboards',
      text: '仪表板',
      url: `datasources/edit/${ds.id}/dashboards`,
    });
  }

  return {
    main: main,
    node: _.find(main.children, { active: true }),
  };
}
